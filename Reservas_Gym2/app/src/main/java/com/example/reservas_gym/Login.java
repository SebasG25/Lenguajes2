package com.example.reservas_gym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private VideoView udemBg;
    private FirebaseAuth mAuth;
    MediaPlayer player;
    int playerVidPos;
    Button btnSignUp, btnLogin;
    String[] userData = new String[1];
    EditText txtId, txtPass;
    DbHelper helper;
    static final String account = "123";
    static final String password = "gymudem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        connect();
        mAuth = FirebaseAuth.getInstance();
        helper = new DbHelper(getApplicationContext(), "BD", null, 1);
        setVideo();
        launchSignUp();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(txtId.getText().toString().trim(), txtPass.getText().toString().trim());
            }
        });
    }

    /*
    Intent que lleva al Menú del administrador
     */
    public void launchAdminPanel()
    {
        Intent intent = Admin_Activity.launcheME(Login.this);
        startActivity(intent);
    }

    public void connect()
    {
        udemBg = findViewById(R.id.video_bg);
        btnSignUp = findViewById(R.id.btnNew_account);
        btnLogin = findViewById(R.id.btnLogin);
        txtId = findViewById(R.id.txtCedula);
        txtPass = findViewById(R.id.txtContraseña);
    }

    /*
    Método para poner el vídeo correctamente en el fondo
     */
    public void setVideo()
    {
        Uri vidUri = Uri.parse("android.resource://" +getPackageName()+ "/" +R.raw.unidem);
        udemBg.setVideoURI(vidUri);
        udemBg.start();
        udemBg.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                player = mp;
                player.setLooping(true);
                if(playerVidPos != 0)
                {
                    player.seekTo(playerVidPos);
                    player.start();
                }
            }
        });
    }

    /*
    Intent para lanzar este activity
     */
    public static Intent launcheME(Context ctx)
    {
        return new Intent(ctx, Login.class);
    }

    /*
    Intent para lanzar el menú de registro
     */
    public void launchSignUp()
    {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = SignUp.launcheME(Login.this);
                startActivity(intent);
            }
        });

    }

    /*
    Intent para lanzar el menú de reservas
     */
    private void launchReserva(String[] user){
        Intent intent = Reservas.launcheME(Login.this);
        intent.putExtra("userData", user);
        startActivity(intent);
    }

    private void login(String email, String password){
        if(email.equalsIgnoreCase("bitas200225@gmail.com") && password.equals("sebas123")){
            txtId.setText("");
            txtPass.setText("");
            Toast.makeText(Login.this, "Bienvenido administrador", Toast.LENGTH_SHORT).show();
            launchAdminPanel();
        }else{
            userData[0] = email;
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                txtId.setText("");
                                txtPass.setText("");
                                launchReserva(userData);
                                Toast.makeText(Login.this, "Se ha ingresado con éxito", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Login.this, "No se pudo completar el ingreso: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

        }
    }

    /*
    Método que verifica si el usuario existe, es el administrador o los campos de los Edit Texts están vacíos
     */
    private void verifyUser(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = txtId.getText().toString().trim();
                String pass = txtPass.getText().toString().trim();
                String name = nameSearchedById(id);
                String lastname = lastnameSearchedById(id);
                String profession = professionSearchedById(id);

                if(id.isEmpty() || pass.isEmpty()) {
                    txtId.setText("");
                    txtPass.setText("");
                    Toast.makeText(Login.this, "Debe llenar todos los espacios obligatoriamente", Toast.LENGTH_SHORT).show();
                }else if(account.equals(id) && password.equals(pass)){
                    txtId.setText("");
                    txtPass.setText("");
                    Toast.makeText(Login.this, "Bienvenido administrador", Toast.LENGTH_SHORT).show();
                    launchAdminPanel();
                }else{
                    if(foundUserAndPassword(id, pass)){
                        txtId.setText("");
                        txtPass.setText("");
                        Toast.makeText(Login.this, "Logueado exitosamente", Toast.LENGTH_SHORT).show();
                        userData[0] = id;
                        userData[1] = name;
                        userData[2] = lastname;
                        userData[3] = profession;
                        launchReserva(userData);
                    }else{
                        txtId.setText("");
                        txtPass.setText("");
                        Toast.makeText(Login.this, "Cédula o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    protected void onPause() {

        super.onPause();
        playerVidPos = player.getCurrentPosition();
        udemBg.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        udemBg.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();
    }

    /*
    Verifica si se encontró el id ingresado como parámetro con su respectiva contraseña
     */
    private boolean foundUserAndPassword(String id, String pass){
        String password = "";
        SQLiteDatabase db = helper.getWritableDatabase();
        String SQL = "Select Password from Estudiantes Where Cedula ='"+id+"'";
        Cursor c = db.rawQuery(SQL, null);
        try{
            if(c.moveToFirst()){
                do{
                    password = c.getString(0);
                }while(c.moveToNext());
            }
            db.close();
        }catch(Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return password.equals(pass);
    }

    /*
    Encuentra un nombre ligado a un id pasado como parámetro
     */
    private String nameSearchedById(String id){
        String name = "";
        SQLiteDatabase db = helper.getWritableDatabase();
        String SQL = "Select Nombre from Estudiantes Where Cedula ='"+id+"'";
        Cursor c = db.rawQuery(SQL, null);
        try{
            if(c.moveToFirst()){
                do{
                    name = c.getString(0);
                }while(c.moveToNext());
            }
            db.close();
        }catch(Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return name;
    }

    /*
    Encuentra un apellido ligado a un id pasado como parámetro
     */
    private String lastnameSearchedById(String id){
        String lastname = "";
        SQLiteDatabase db = helper.getWritableDatabase();
        String SQL = "Select Apellido from Estudiantes Where Cedula ='"+id+"'";
        Cursor c = db.rawQuery(SQL, null);
        try{
            if(c.moveToFirst()){
                do{
                    lastname = c.getString(0);
                }while(c.moveToNext());
            }
            db.close();
        }catch(Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return lastname;
    }

    private String professionSearchedById(String id){
        String profession = "";
        SQLiteDatabase db = helper.getWritableDatabase();
        String SQL = "Select Carrera from Estudiantes Where Cedula ='"+id+"'";
        Cursor c = db.rawQuery(SQL, null);
        try{
            if(c.moveToFirst()){
                do{
                    profession = c.getString(0);
                }while(c.moveToNext());
            }
            db.close();
        }catch(Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return profession;
    }
}
