package com.example.practica;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    int contJugadores = 0;
    private String nombreJugador1;
    private String nombreJugador2;
    private String[] nombres = new String[2];
    EditText txtCorreo, txtContraseña;
    Button btnRegistro, btnLogin;
    Carchivos archivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        conectar();
        contJugadores = 1;
        archivo = new Carchivos(this, "Cuentas.txt");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtCorreo.getText().toString().isEmpty()|| txtContraseña.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Debe llenar obligatoriamente todos los campos", Toast.LENGTH_LONG).show();
                } else {
                    String correo = txtCorreo.getText().toString().trim();
                    String contraseña = txtContraseña.getText().toString().trim();
                    if (encontroUsuarioYContraseña(correo, contraseña)) {
                        try {
                            if(contJugadores%2 != 0){
                                setNombreJugador1(correo);
                                nombres[0] = nombreJugador1;

                            }else{
                                setNombreJugador2(correo);
                                nombres[1] = nombreJugador2;
                                Intent vistaTablero = new Intent(getApplicationContext(), TableroActivity.class);
                                vistaTablero.putExtra("Nombres", nombres);
                                startActivity(vistaTablero);
                            }
                            Toast.makeText(LoginActivity.this, "Usuario Logueado", Toast.LENGTH_SHORT).show();
                            txtCorreo.setText("");
                            txtContraseña.setText("");
                            contJugadores++;
                        } catch (Exception e) {
                            Log.e("", e.getMessage());
                        }
                    } else {
                        txtCorreo.setText("");
                        txtContraseña.setText("");
                        Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtCorreo.getText().toString().isEmpty() || txtContraseña.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Debe llenar obligatoriamente todos los campos", Toast.LENGTH_LONG).show();
                } else {
                    String correo = txtCorreo.getText().toString();
                    String contraseña = txtContraseña.getText().toString();
                    String texto = correo + "\n" + contraseña + "\n";


                    txtCorreo.setText("");
                    txtContraseña.setText("");

                    if(!encontroUsuario(correo)){
                        try {
                            archivo.escribir(texto);
                            Toast.makeText(LoginActivity.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Log.e("", e.getMessage());
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, "Este correo ya existe", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean encontroUsuario(String correo){
        for(int i = 0; i < archivo.listaUsuarios().size(); i++){
            if(archivo.listaUsuarios().get(i).getNombre().equals(correo.trim())){
                return true;
            }
        }
        return false;
    }

    private boolean encontroUsuarioYContraseña(String correo, String contraseña){
        for(int i = 0; i < archivo.listaUsuarios().size(); i++){
            if(archivo.listaUsuarios().get(i).getNombre().equals(correo.trim())){
                if(archivo.listaUsuarios().get(i).getContraseña().equals(contraseña.trim())){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }

    private void conectar() {
        txtCorreo = findViewById(R.id.txtCorreo);
        txtContraseña = findViewById(R.id.txtContraseña);
        btnLogin = findViewById(R.id.btnIniciarSesion);
        btnRegistro = findViewById(R.id.btnRegistro);
    }

    public void setNombreJugador1(String nombreJugador1) {
        this.nombreJugador1 = nombreJugador1;
    }

    public void setNombreJugador2(String nombreJugador2) {
        this.nombreJugador2 = nombreJugador2;
    }
}
