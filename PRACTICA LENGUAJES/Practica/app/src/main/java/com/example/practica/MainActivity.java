package com.example.practica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnJugar, btnPuntajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conectar();

        /*
            Se dirige a la pantalla de Login cuando se presiona el botón "Jugar"
         */
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vistaLogin = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(vistaLogin);
            }
        });

        /*
            Se dirige a la pantalla de Puntajes cuando se presiona el botón "Puntajes"
         */
        btnPuntajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vistaPuntajes = new Intent(getApplicationContext(), PuntajeActivity.class);
                startActivity(vistaPuntajes);
            }
        });
    }

    private void conectar() {
        btnJugar = findViewById(R.id.btnJugar);
        btnPuntajes = findViewById(R.id.btnPuntaje);
    }
}
