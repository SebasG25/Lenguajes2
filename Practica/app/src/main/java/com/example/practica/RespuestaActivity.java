package com.example.practica;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class RespuestaActivity extends AppCompatActivity {

    Random random = new Random();
    Button btnOpcion1, btnOpcion2, btnOpcion3, btnOpcion4;
    TextView tvPregunta;
    Carchivos archivo;
    int puntaje;
    Pregunta preguntaActual;
    String respuestaCorrecta;
    String respuestaUsuario;
    String numeroDeBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respuesta);
        conectar();
        Bundle recup = getIntent().getExtras();
        numeroDeBoton = recup.getString("boton");
        archivo= (Carchivos) recup.getSerializable("arraylist");
        preguntaActual = sortearPregunta();
        tvPregunta.setText(preguntaActual.getPregunta());
        btnOpcion1.setText(preguntaActual.getOpcion1());
        btnOpcion2.setText(preguntaActual.getOpcion2());
        btnOpcion3.setText(preguntaActual.getOpcion3());
        btnOpcion4.setText(preguntaActual.getOpcion4());
        respuestaCorrecta = preguntaActual.getOpcionCorrecta();
        puntaje = preguntaActual.getPuntaje();

        btnOpcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuestaUsuario = btnOpcion1.getText().toString().trim();
                if(respuestaValida()){
                    Intent tablero = new Intent(getApplicationContext(), TableroActivity.class);
                    tablero.putExtra("respuesta", "correcta");
                    tablero.putExtra("boton", numeroDeBoton);
                    listaPreguntas().remove(getPosicionPregunta(listaPreguntas(), preguntaActual));
                    tablero.putExtra("arraylist",archivo);
                    startActivity(tablero);
                    finish();
                }else{
                    Intent tablero = new Intent(getApplicationContext(), TableroActivity.class);
                    tablero.putExtra("respuesta", "incorrecta");
                    tablero.putExtra("boton", numeroDeBoton);
                    listaPreguntas().remove(getPosicionPregunta(listaPreguntas(), preguntaActual));
                    tablero.putExtra("arraylist",archivo);
                    startActivity(tablero);
                    finish();
                }
            }
        });
        btnOpcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuestaUsuario = btnOpcion2.getText().toString().trim();
                if(respuestaValida()){
                    Intent tablero = new Intent(getApplicationContext(), TableroActivity.class);
                    tablero.putExtra("respuesta", "correcta");
                    tablero.putExtra("boton", numeroDeBoton);
                    listaPreguntas().remove(getPosicionPregunta(listaPreguntas(), preguntaActual));
                    tablero.putExtra("arraylist",archivo);
                    startActivity(tablero);
                    finish();
                }else{
                    Intent tablero = new Intent(getApplicationContext(), TableroActivity.class);
                    tablero.putExtra("respuesta", "incorrecta");
                    tablero.putExtra("boton", numeroDeBoton);
                    listaPreguntas().remove(getPosicionPregunta(listaPreguntas(), preguntaActual));
                    tablero.putExtra("arraylist",archivo);
                    startActivity(tablero);
                    finish();
                }
            }
        });
        btnOpcion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuestaUsuario = btnOpcion3.getText().toString().trim();
                if(respuestaValida()){
                    Intent tablero = new Intent(getApplicationContext(), TableroActivity.class);
                    tablero.putExtra("respuesta", "correcta");
                    tablero.putExtra("boton", numeroDeBoton);
                    listaPreguntas().remove(getPosicionPregunta(listaPreguntas(), preguntaActual));
                    tablero.putExtra("arraylist",archivo);
                    startActivity(tablero);
                    finish();
                }else{
                    Intent tablero = new Intent(getApplicationContext(), TableroActivity.class);
                    tablero.putExtra("respuesta", "incorrecta");
                    tablero.putExtra("boton", numeroDeBoton);
                    listaPreguntas().remove(getPosicionPregunta(listaPreguntas(), preguntaActual));
                    tablero.putExtra("arraylist",archivo);
                    startActivity(tablero);
                    finish();
                }
            }
        });
        btnOpcion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuestaUsuario = btnOpcion4.getText().toString().trim();
                if(respuestaValida()){
                    Intent tablero = new Intent(getApplicationContext(), TableroActivity.class);
                    tablero.putExtra("respuesta", "correcta");
                    tablero.putExtra("boton", numeroDeBoton);
                    listaPreguntas().remove(getPosicionPregunta(listaPreguntas(), preguntaActual));
                    tablero.putExtra("arraylist",archivo);
                    startActivity(tablero);
                    finish();
                }else{
                    Intent tablero = new Intent(getApplicationContext(), TableroActivity.class);
                    tablero.putExtra("respuesta", "incorrecta");
                    tablero.putExtra("boton", numeroDeBoton);
                    listaPreguntas().remove(getPosicionPregunta(listaPreguntas(), preguntaActual));
                    tablero.putExtra("arraylist",archivo);
                    startActivity(tablero);
                    finish();
                }
            }
        });

    }

    private void conectar() {
        btnOpcion1 = findViewById(R.id.btnOpcion1);
        btnOpcion2 = findViewById(R.id.btnOpcion2);
        btnOpcion3 = findViewById(R.id.btnOpcion3);
        btnOpcion4 = findViewById(R.id.btnOpcion4);
        tvPregunta = findViewById(R.id.tvPregunta);
    }

    private Pregunta sortearPregunta(){
        int posicion = random.nextInt(listaPreguntas().size());
        Pregunta pregunta = listaPreguntas().get(posicion);
        return pregunta;
    }

    private boolean respuestaValida(){
        if(preguntaActual.getOpcionCorrecta().equals(respuestaUsuario)){
            return true;
        }else{
            return false;
        }
    }

    private ArrayList<Pregunta> listaPreguntas(){
        return archivo.listaPreguntas();
    }

    private Pregunta getPosicionPregunta(ArrayList<Pregunta> preguntas, Pregunta pregunta){
        for(int i = 0; i < preguntas.size(); i++){
            if(preguntas.get(i) == pregunta){
                return preguntas.get(i);
            }
        }
        return null;
    }
}
