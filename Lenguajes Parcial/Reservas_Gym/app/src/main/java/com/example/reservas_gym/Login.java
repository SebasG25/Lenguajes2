package com.example.reservas_gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.jackandphantom.blurimage.BlurImage;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private VideoView udemBg;
    MediaPlayer player;
    EditText txtCedula, txtContraseña;
    Button btnLogin, btnCrearCuenta;
    int playerVidPos;
    Archivos archivos;
    ArrayList<Usuario> listaUsuarios = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BlurImage.with(getApplicationContext());
        connect();
        setVideo();
        archivos = new Archivos(getApplicationContext(), "accounts.txt");
        listaUsuarios = getListaUsuarios();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cedula = txtCedula.getText().toString().trim();
                String contraseña = txtContraseña.getText().toString().trim();
                if(cedula.isEmpty() || contraseña.isEmpty()){
                    Toast.makeText(Login.this, "Debe llenar todos los campos obligatoriamente", Toast.LENGTH_SHORT).show();
                }else{
                    if(encontroUsuarioYContraseña(cedula, contraseña)){
                        /*
                            Intent hacia el calendario
                         */
                    }else{
                        Toast.makeText(Login.this, "Cédula o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void connect()
    {
        udemBg = findViewById(R.id.video_bg);
        txtCedula = findViewById(R.id.txtCedula);
        txtContraseña = findViewById(R.id.txtContraseña);
        btnCrearCuenta = findViewById(R.id.btnCrearCuenta);
        btnLogin = findViewById(R.id.btnLogin);

    }
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
    public static Intent launcheME(Context ctx)
    {
        return new Intent(ctx, Login.class);
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

    private boolean encontroUsuarioYContraseña(String cedula, String contraseña){
        for(int i = 0; i < listaUsuarios.size(); i++){
            if(listaUsuarios.get(i).getCedula().equals(cedula.trim())){
                if(listaUsuarios.get(i).getContraseña().equals(contraseña.trim())){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }

    private ArrayList<Usuario> getListaUsuarios(){
        return archivos.listaUsuarios();
    }
}
