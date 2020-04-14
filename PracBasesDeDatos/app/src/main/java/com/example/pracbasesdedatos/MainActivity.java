package com.example.pracbasesdedatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnMenuRegistro, btnMenuConsultas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        conectar();
        btnMenuRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IngresarRegistro();
            }
        });
        btnMenuConsultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IngresarConsultas();
            }
        });
    }

    private void IngresarRegistro(){
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }

    private void IngresarConsultas(){
        Intent intent = new Intent(this, ConsultasActivity.class);
        startActivity(intent);
    }

    private void conectar() {
        btnMenuRegistro = findViewById(R.id.btnMenuRegistro);
        btnMenuConsultas = findViewById(R.id.btnMenuConsultas);
    }


}
