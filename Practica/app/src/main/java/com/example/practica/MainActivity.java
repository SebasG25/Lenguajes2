package com.example.practica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnJugar, btnRegistro, btnPuntajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conectar();

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vistaLogin = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(vistaLogin);
            }
        });
    }

    private void conectar() {
        btnJugar = findViewById(R.id.btnJugar);
        btnRegistro = findViewById(R.id.btnRegistro);
        btnPuntajes = findViewById(R.id.btnPuntaje);
    }
}
