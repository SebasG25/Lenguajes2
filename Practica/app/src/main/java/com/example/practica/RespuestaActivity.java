package com.example.practica;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RespuestaActivity extends AppCompatActivity {

    Button btnOpcion1, btnOpcion2, btnOpcion3, btnOpcion4;
    TextView tvPregunta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respuesta);
        conectar();
    }

    private void conectar() {
        btnOpcion1 = findViewById(R.id.btnOpcion1);
        btnOpcion2 = findViewById(R.id.btnOpcion2);
        btnOpcion3 = findViewById(R.id.btnOpcion3);
        btnOpcion4 = findViewById(R.id.btnOpcion4);
        tvPregunta = findViewById(R.id.tvPregunta);
    }
}
