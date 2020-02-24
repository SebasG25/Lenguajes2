package com.example.practica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnUnJugador, btnDosJugadores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conectar();

        btnUnJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vistaUnJugador = new Intent(getApplicationContext(), UnJugadorActivity.class);
                startActivity(vistaUnJugador);
            }
        });
    }

    private void conectar() {
        btnUnJugador = findViewById(R.id.btnUnJugador);
        btnDosJugadores = findViewById(R.id.btnDosJugadores);
    }
}
