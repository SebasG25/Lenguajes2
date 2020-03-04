package com.example.practica;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class TableroActivity extends AppCompatActivity {
    int contTurno = 0;
    String[] nombresJugadores = new String[2];
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn20;
    TextView nombreJugador1, nombreJugador2;
    Carchivos archivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero);
        conectar();
        Bundle recup = getIntent().getExtras();
        nombresJugadores = recup.getStringArray("Nombres");
        nombreJugador1.setText(nombresJugadores[0]);
        nombreJugador2.setText(nombresJugadores[1]);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton1();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton2();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton3();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton4();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton5();
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton6();
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton7();
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton8();
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton9();
            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton10();
            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton11();
            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton12();
            }
        });

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton13();
            }
        });

        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton14();
            }
        });

        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton15();
            }
        });

        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton16();
            }
        });

        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton17();
            }
        });

        btn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton18();
            }
        });

        btn19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton19();
            }
        });

        btn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton20();
            }
        });
    }

    private void boton1(){
        Intent respuesta = new Intent(getApplicationContext(), RespuestaActivity.class);
        startActivity(respuesta);
    }

    private void boton2(){
        Intent respuesta = new Intent(getApplicationContext(), RespuestaActivity.class);
        startActivity(respuesta);
    }

    private void boton3(){
        Intent respuesta = new Intent(getApplicationContext(), RespuestaActivity.class);
        startActivity(respuesta);
    }

    private void boton4(){
        Intent respuesta = new Intent(getApplicationContext(), RespuestaActivity.class);
        startActivity(respuesta);
    }

    private void boton5(){
        Intent respuesta = new Intent(getApplicationContext(), RespuestaActivity.class);
        startActivity(respuesta);
    }

    private void boton6(){
        Intent respuesta = new Intent(getApplicationContext(), RespuestaActivity.class);
        startActivity(respuesta);
    }

    private void boton7(){
        Intent respuesta = new Intent(getApplicationContext(), RespuestaActivity.class);
        startActivity(respuesta);
    }

    private void boton8(){
        Intent respuesta = new Intent(getApplicationContext(), RespuestaActivity.class);
        startActivity(respuesta);
    }

    private void boton9(){
        Intent respuesta = new Intent(getApplicationContext(), RespuestaActivity.class);
        startActivity(respuesta);
    }

    private void boton10(){
        Intent respuesta = new Intent(getApplicationContext(), RespuestaActivity.class);
        startActivity(respuesta);
    }

    private void boton11(){
        Intent respuesta = new Intent(getApplicationContext(), RespuestaActivity.class);
        startActivity(respuesta);
    }

    private void boton12(){
        Intent respuesta = new Intent(getApplicationContext(), RespuestaActivity.class);
        startActivity(respuesta);
    }

    private void boton13(){
        Intent respuesta = new Intent(getApplicationContext(), RespuestaActivity.class);
        startActivity(respuesta);
    }

    private void boton14(){
        Intent respuesta = new Intent(getApplicationContext(), RespuestaActivity.class);
        startActivity(respuesta);
    }

    private void boton15(){
        Intent respuesta = new Intent(getApplicationContext(), RespuestaActivity.class);
        startActivity(respuesta);
    }

    private void boton16(){
        Intent respuesta = new Intent(getApplicationContext(), RespuestaActivity.class);
        startActivity(respuesta);
    }

    private void boton17(){
        Intent respuesta = new Intent(getApplicationContext(), RespuestaActivity.class);
        startActivity(respuesta);
    }

    private void boton18(){
        Intent respuesta = new Intent(getApplicationContext(), RespuestaActivity.class);
        startActivity(respuesta);
    }

    private void boton19(){
        Intent respuesta = new Intent(getApplicationContext(), RespuestaActivity.class);
        startActivity(respuesta);
    }

    private void boton20(){
        Intent respuesta = new Intent(getApplicationContext(), RespuestaActivity.class);
        startActivity(respuesta);
    }

    private boolean esTurnoJugador1(){
        return contTurno % 2 == 0;
    }

    private boolean esTurnoJugador2(){
        return contTurno % 2 == 1;
    }

    private void conectar() {
        nombreJugador1 = findViewById(R.id.tvNombreJugador1);
        nombreJugador2 = findViewById(R.id.tvNombreJugador2);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn10 = findViewById(R.id.btn10);
        btn11 = findViewById(R.id.btn11);
        btn12 = findViewById(R.id.btn12);
        btn13 = findViewById(R.id.btn13);
        btn14 = findViewById(R.id.btn14);
        btn15 = findViewById(R.id.btn15);
        btn16 = findViewById(R.id.btn16);
        btn17 = findViewById(R.id.btn17);
        btn18 = findViewById(R.id.btn18);
        btn19 = findViewById(R.id.btn19);
        btn20 = findViewById(R.id.btn20);
    }

    private Pregunta sortearPregunta(){
        Random random = new Random();
        int posicion = random.nextInt(listaPreguntas().size());
        Pregunta pregunta = listaPreguntas().get(posicion);
        listaPreguntas().remove(posicion);
        return pregunta;
    }

    private ArrayList<Pregunta> listaPreguntas(){
        return archivo.listaPreguntas();
    }
}
