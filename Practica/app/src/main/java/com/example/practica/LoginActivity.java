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
    EditText txtCorreo, txtContraseña;
    Button btnRegistro, btnLogin;
    Carchivos archivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        conectar();
        archivo = new Carchivos(this, "Cuentas.txt");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtCorreo.getText().toString() == "" || txtContraseña.getText().toString() == "") {
                    Toast.makeText(LoginActivity.this, "Debe llenar obligatoriamente todos los campos", Toast.LENGTH_LONG).show();
                } else {
                    String correo = txtCorreo.getText().toString();
                    String contraseña = txtContraseña.getText().toString();
                    if (archivo.buscarCuenta(correo, contraseña)) {
                        try {
                            if(contJugadores == 0){
                                setNombreJugador1(correo);
                            }else{
                                setNombreJugador2(correo);
                            }
                            Toast.makeText(LoginActivity.this, "Usuario Logueado", Toast.LENGTH_SHORT).show();
                            txtCorreo.setText("");
                            txtContraseña.setText("");
                            contJugadores++;

                            if (contJugadores == 2) {
                                Intent vistaTablero = new Intent(getApplicationContext(), TableroActivity.class);
                                startActivity(vistaTablero);
                            }
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
                    String texto = "\n\n" + correo + "\n" + contraseña;

                    txtCorreo.setText("");
                    txtContraseña.setText("");

                    if(!archivo.buscarCuenta(correo, contraseña)){
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

    private void conectar() {
        txtCorreo = findViewById(R.id.txtCorreo);
        txtContraseña = findViewById(R.id.txtContraseña);
        btnLogin = findViewById(R.id.btnIniciarSesion);
        btnRegistro = findViewById(R.id.btnRegistro);
    }

    public String getNombreJugador1() {
        return nombreJugador1;
    }

    public void setNombreJugador1(String nombreJugador1) {
        this.nombreJugador1 = nombreJugador1;
    }

    public String getNombreJugador2() {
        return nombreJugador2;
    }

    public void setNombreJugador2(String nombreJugador2) {
        this.nombreJugador2 = nombreJugador2;
    }
}
