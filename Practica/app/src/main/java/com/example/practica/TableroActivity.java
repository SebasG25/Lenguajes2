package com.example.practica;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TableroActivity extends AppCompatActivity {
    Random random = new Random();
    Usuario jug1, jug2;
    int cantidadPreguntas, puntajeActual, contTurno = 0;
    Pregunta preguntaActual;
    String respuestaUsuario, correcta;
    String[] botones = new String[20];
    ArrayList<Pregunta> preguntas;
    String[] nombresJugadores = new String[2];
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19,
            btn20, btnOpcion1, btnOpcion2, btnOpcion3, btnOpcion4, actual;
    TextView nombreJugador1, nombreJugador2, tvPregunta, tvPuntaje1, tvPuntaje2;
    Carchivos archivos, archivosPuntaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero);
        conectar();
        archivosPuntaje = new Carchivos(this, "Puntajes.txt");
        archivos = new Carchivos(this, "Preguntas.txt");
        Bundle recup = getIntent().getExtras();
        nombresJugadores = recup.getStringArray("Nombres");
        nombreJugador1.setText(nombresJugadores[0]);
        nombreJugador2.setText(nombresJugadores[1]);
        jug1 = new Usuario(nombresJugadores[0]);
        jug2 = new Usuario(nombresJugadores[1]);

        preguntas = listaPreguntas();
        cantidadPreguntas = preguntas.size();

        btnOpcion1.setEnabled(false);
        btnOpcion2.setEnabled(false);
        btnOpcion3.setEnabled(false);
        btnOpcion4.setEnabled(false);

        btnOpcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOpcion1.setEnabled(false);
                btnOpcion2.setEnabled(false);
                btnOpcion3.setEnabled(false);
                btnOpcion4.setEnabled(false);
                respuestaUsuario = btnOpcion1.getText().toString().trim();
                tvPregunta.setText("Pregunta");
                btnOpcion1.setText("Opcion 1");
                btnOpcion2.setText("Opcion 2");
                btnOpcion3.setText("Opcion 3");
                btnOpcion4.setText("Opcion 4");
                /*
                    Verifico si la respuesta es válida y que jugador la acertó. Si acertó la respuesta, se le da acumula el puntaje, se elimina la pregunta
                    y se colorea el boton de verde, sino, no se acumula el puntaje, se elimina la pregunta y se colorea el boton de rojo
                 */
                /*
                    Se pasa el turno
                 */
                if (respuestaValida()) {

                    if (esTurnoJugador1()) {
                        jug1.setPuntaje(preguntaActual.getPuntaje());
                        tvPuntaje1.setText(jug1.getPuntaje() + "");

                    } else if (esTurnoJugador2()) {
                        jug2.setPuntaje(preguntaActual.getPuntaje());
                        tvPuntaje2.setText(jug2.getPuntaje() + "");

                    }
                    contTurno++;
                    Toast.makeText(TableroActivity.this, "RESPUESTA CORRECTA", Toast.LENGTH_SHORT).show();
                    actual.setEnabled(false);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        actual.setBackground(getDrawable(R.drawable.verde));
                    }
                    preguntas.remove(getPosicionPregunta(preguntas, preguntaActual));
                    if (revisarVictoria()) {
                        if(getJugadorGanador() == null){
                            Toast.makeText(TableroActivity.this, "EMPATE" + getJugadorGanador().getNombre(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(TableroActivity.this, "GANA " + getJugadorGanador().getNombre(), Toast.LENGTH_SHORT).show();
                            String nombre = getJugadorGanador().getNombre();
                            String puntaje = getJugadorGanador().getPuntaje() + "";
                            String texto = nombre + "\n" + puntaje + "\n";
                            try {
                                archivosPuntaje.escribir(texto);
                            } catch (IOException e) {
                                Log.e("", e.getMessage());
                            }
                        }
                    }
                } else {
                    contTurno++;
                    actual.setEnabled(false);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        actual.setBackground(getDrawable(R.drawable.rojo));
                    }
                    preguntas.remove(getPosicionPregunta(preguntas, preguntaActual));
                    Toast.makeText(TableroActivity.this, "RESPUESTA INCORRECTA", Toast.LENGTH_SHORT).show();
                    if (revisarVictoria()) {
                        if(getJugadorGanador() == null){
                            Toast.makeText(TableroActivity.this, "EMPATE" + getJugadorGanador().getNombre(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(TableroActivity.this, "GANA " + getJugadorGanador().getNombre(), Toast.LENGTH_SHORT).show();
                            String nombre = getJugadorGanador().getNombre();
                            String puntaje = getJugadorGanador().getPuntaje() + "";
                            String texto = nombre + "\n" + puntaje + "\n";
                            try {
                                archivosPuntaje.escribir(texto);
                            } catch (IOException e) {
                                Log.e("", e.getMessage());
                            }
                        }
                    }
                }
            }
        });

        btnOpcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOpcion1.setEnabled(false);
                btnOpcion2.setEnabled(false);
                btnOpcion3.setEnabled(false);
                btnOpcion4.setEnabled(false);
                respuestaUsuario = btnOpcion2.getText().toString().trim();
                tvPregunta.setText("Pregunta");
                btnOpcion1.setText("Opcion 1");
                btnOpcion2.setText("Opcion 2");
                btnOpcion3.setText("Opcion 3");
                btnOpcion4.setText("Opcion 4");
                if (respuestaValida()) {

                    if (esTurnoJugador1()) {
                        jug1.setPuntaje(preguntaActual.getPuntaje());
                        tvPuntaje1.setText(jug1.getPuntaje() + "");

                    } else if (esTurnoJugador2()) {
                        jug2.setPuntaje(preguntaActual.getPuntaje());
                        tvPuntaje2.setText(jug2.getPuntaje() + "");

                    }
                    contTurno++;
                    actual.setEnabled(false);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        actual.setBackground(getDrawable(R.drawable.verde));
                    }
                    preguntas.remove(getPosicionPregunta(preguntas, preguntaActual));
                    Toast.makeText(TableroActivity.this, "RESPUESTA CORRECTA", Toast.LENGTH_SHORT).show();
                    if (revisarVictoria()) {
                        if(getJugadorGanador() == null){
                            Toast.makeText(TableroActivity.this, "EMPATE" + getJugadorGanador().getNombre(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(TableroActivity.this, "GANA " + getJugadorGanador().getNombre(), Toast.LENGTH_SHORT).show();
                            String nombre = getJugadorGanador().getNombre();
                            String puntaje = getJugadorGanador().getPuntaje() + "";
                            String texto = nombre + "\n" + puntaje + "\n";
                            try {
                                archivosPuntaje.escribir(texto);
                            } catch (IOException e) {
                                Log.e("", e.getMessage());
                            }
                        }
                    }
                } else {

                    contTurno++;
                    actual.setEnabled(false);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        actual.setBackground(getDrawable(R.drawable.rojo));
                    }
                    preguntas.remove(getPosicionPregunta(preguntas, preguntaActual));
                    Toast.makeText(TableroActivity.this, "RESPUESTA INCORRECTA", Toast.LENGTH_SHORT).show();
                    if (revisarVictoria()) {
                        if(getJugadorGanador() == null){
                            Toast.makeText(TableroActivity.this, "EMPATE" + getJugadorGanador().getNombre(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(TableroActivity.this, "GANA " + getJugadorGanador().getNombre(), Toast.LENGTH_SHORT).show();
                            String nombre = getJugadorGanador().getNombre();
                            String puntaje = getJugadorGanador().getPuntaje() + "";
                            String texto = nombre + "\n" + puntaje + "\n";
                            try {
                                archivosPuntaje.escribir(texto);
                            } catch (IOException e) {
                                Log.e("", e.getMessage());
                            }
                        }
                    }
                }
            }
        });

        btnOpcion3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                btnOpcion1.setEnabled(false);
                btnOpcion2.setEnabled(false);
                btnOpcion3.setEnabled(false);
                btnOpcion4.setEnabled(false);
                respuestaUsuario = btnOpcion3.getText().toString().trim();
                tvPregunta.setText("Pregunta");
                btnOpcion1.setText("Opcion 1");
                btnOpcion2.setText("Opcion 2");
                btnOpcion3.setText("Opcion 3");
                btnOpcion4.setText("Opcion 4");
                if (respuestaValida()) {

                    if (esTurnoJugador1()) {
                        jug1.setPuntaje(preguntaActual.getPuntaje());
                        tvPuntaje1.setText(jug1.getPuntaje() + "");

                    } else if (esTurnoJugador2()) {
                        jug2.setPuntaje(preguntaActual.getPuntaje());
                        tvPuntaje2.setText(jug2.getPuntaje() + "");

                    }
                    contTurno++;
                    actual.setEnabled(false);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        actual.setBackground(getDrawable(R.drawable.rojo));
                    }
                    preguntas.remove(getPosicionPregunta(preguntas, preguntaActual));
                    Toast.makeText(TableroActivity.this, "RESPUESTA CORRECTA", Toast.LENGTH_SHORT).show();
                    if (revisarVictoria()) {
                        if(getJugadorGanador() == null){
                            Toast.makeText(TableroActivity.this, "EMPATE" + getJugadorGanador().getNombre(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(TableroActivity.this, "GANA " + getJugadorGanador().getNombre(), Toast.LENGTH_SHORT).show();
                            String nombre = getJugadorGanador().getNombre();
                            String puntaje = getJugadorGanador().getPuntaje() + "";
                            String texto = nombre + "\n" + puntaje + "\n";
                            try {
                                archivosPuntaje.escribir(texto);
                            } catch (IOException e) {
                                Log.e("", e.getMessage());
                            }
                        }
                    }
                } else {

                    contTurno++;
                    actual.setEnabled(false);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        actual.setBackground(getDrawable(R.drawable.verde));
                    }
                    preguntas.remove(getPosicionPregunta(preguntas, preguntaActual));
                    Toast.makeText(TableroActivity.this, "RESPUESTA INCORRECTA", Toast.LENGTH_SHORT).show();
                    if (revisarVictoria()) {
                        if(getJugadorGanador() == null){
                            Toast.makeText(TableroActivity.this, "EMPATE" + getJugadorGanador().getNombre(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(TableroActivity.this, "GANA " + getJugadorGanador().getNombre(), Toast.LENGTH_SHORT).show();
                            String nombre = getJugadorGanador().getNombre();
                            String puntaje = getJugadorGanador().getPuntaje() + "";
                            String texto = nombre + "\n" + puntaje + "\n";
                            try {
                                archivosPuntaje.escribir(texto);
                            } catch (IOException e) {
                                Log.e("", e.getMessage());
                            }
                        }
                    }
                }
            }
        });

        btnOpcion4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                btnOpcion1.setEnabled(false);
                btnOpcion2.setEnabled(false);
                btnOpcion3.setEnabled(false);
                btnOpcion4.setEnabled(false);
                respuestaUsuario = btnOpcion4.getText().toString().trim();
                tvPregunta.setText("Pregunta");
                btnOpcion1.setText("Opcion 1");
                btnOpcion2.setText("Opcion 2");
                btnOpcion3.setText("Opcion 3");
                btnOpcion4.setText("Opcion 4");
                if (respuestaValida()) {

                    if (esTurnoJugador1()) {
                        jug1.setPuntaje(preguntaActual.getPuntaje());
                        tvPuntaje1.setText(jug1.getPuntaje() + "");

                    } else if (esTurnoJugador2()) {
                        jug2.setPuntaje(preguntaActual.getPuntaje());
                        tvPuntaje2.setText(jug2.getPuntaje() + "");

                    }
                    contTurno++;
                    actual.setEnabled(false);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        actual.setBackground(getDrawable(R.drawable.verde));
                    }
                    preguntas.remove(getPosicionPregunta(preguntas, preguntaActual));
                    Toast.makeText(TableroActivity.this, "RESPUESTA CORRECTA", Toast.LENGTH_SHORT).show();
                    if (revisarVictoria()) {
                        if(getJugadorGanador() == null){
                            Toast.makeText(TableroActivity.this, "EMPATE" + getJugadorGanador().getNombre(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(TableroActivity.this, "GANA " + getJugadorGanador().getNombre(), Toast.LENGTH_SHORT).show();
                            String nombre = getJugadorGanador().getNombre();
                            String puntaje = getJugadorGanador().getPuntaje() + "";
                            String texto = nombre + "\n" + puntaje + "\n";
                            try {
                                archivosPuntaje.escribir(texto);
                            } catch (IOException e) {
                                Log.e("", e.getMessage());
                            }
                        }
                    }
                } else {

                    contTurno++;
                    actual.setEnabled(false);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        actual.setBackground(getDrawable(R.drawable.rojo));
                    }
                    preguntas.remove(getPosicionPregunta(preguntas, preguntaActual));
                    Toast.makeText(TableroActivity.this, "RESPUESTA INCORRECTA", Toast.LENGTH_SHORT).show();
                    if (revisarVictoria()) {
                        if(getJugadorGanador() == null){
                            Toast.makeText(TableroActivity.this, "EMPATE" + getJugadorGanador().getNombre(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(TableroActivity.this, "GANA " + getJugadorGanador().getNombre(), Toast.LENGTH_SHORT).show();
                            String nombre = getJugadorGanador().getNombre();
                            String puntaje = getJugadorGanador().getPuntaje() + "";
                            String texto = nombre + "\n" + puntaje + "\n";
                            try {
                                archivosPuntaje.escribir(texto);
                            } catch (IOException e) {
                                Log.e("", e.getMessage());
                            }
                        }
                    }
                }
            }
        });

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

    /*
        Se obtienen los atributos de la clase pregunta (La pregunta, las 4 respuestas, el puntaje y la opción correcta)
     */
    private void boton1() {
        actual = btn1;
        preguntaActual = sortearPregunta();
        puntajeActual = preguntaActual.getPuntaje();
        correcta = preguntaActual.getOpcionCorrecta();
        tvPregunta.setText(preguntaActual.getPregunta());
        btnOpcion1.setEnabled(true);
        btnOpcion2.setEnabled(true);
        btnOpcion3.setEnabled(true);
        btnOpcion4.setEnabled(true);
        btnOpcion1.setText(preguntaActual.getOpcion1());
        btnOpcion2.setText(preguntaActual.getOpcion2());
        btnOpcion3.setText(preguntaActual.getOpcion3());
        btnOpcion4.setText(preguntaActual.getOpcion4());
    }

    private void boton2() {
        actual = btn2;
        preguntaActual = sortearPregunta();
        puntajeActual = preguntaActual.getPuntaje();
        correcta = preguntaActual.getOpcionCorrecta();
        tvPregunta.setText(preguntaActual.getPregunta());
        btnOpcion1.setEnabled(true);
        btnOpcion2.setEnabled(true);
        btnOpcion3.setEnabled(true);
        btnOpcion4.setEnabled(true);
        btnOpcion1.setText(preguntaActual.getOpcion1());
        btnOpcion2.setText(preguntaActual.getOpcion2());
        btnOpcion3.setText(preguntaActual.getOpcion3());
        btnOpcion4.setText(preguntaActual.getOpcion4());
    }

    private void boton3() {
        actual = btn3;
        preguntaActual = sortearPregunta();
        puntajeActual = preguntaActual.getPuntaje();
        correcta = preguntaActual.getOpcionCorrecta();
        tvPregunta.setText(preguntaActual.getPregunta());
        btnOpcion1.setEnabled(true);
        btnOpcion2.setEnabled(true);
        btnOpcion3.setEnabled(true);
        btnOpcion4.setEnabled(true);
        btnOpcion1.setText(preguntaActual.getOpcion1());
        btnOpcion2.setText(preguntaActual.getOpcion2());
        btnOpcion3.setText(preguntaActual.getOpcion3());
        btnOpcion4.setText(preguntaActual.getOpcion4());
    }

    private void boton4() {
        actual = btn4;
        preguntaActual = sortearPregunta();
        puntajeActual = preguntaActual.getPuntaje();
        correcta = preguntaActual.getOpcionCorrecta();
        tvPregunta.setText(preguntaActual.getPregunta());
        btnOpcion1.setEnabled(true);
        btnOpcion2.setEnabled(true);
        btnOpcion3.setEnabled(true);
        btnOpcion4.setEnabled(true);
        btnOpcion1.setText(preguntaActual.getOpcion1());
        btnOpcion2.setText(preguntaActual.getOpcion2());
        btnOpcion3.setText(preguntaActual.getOpcion3());
        btnOpcion4.setText(preguntaActual.getOpcion4());
    }

    private void boton5() {
        actual = btn5;
        preguntaActual = sortearPregunta();
        puntajeActual = preguntaActual.getPuntaje();
        correcta = preguntaActual.getOpcionCorrecta();
        tvPregunta.setText(preguntaActual.getPregunta());
        btnOpcion1.setEnabled(true);
        btnOpcion2.setEnabled(true);
        btnOpcion3.setEnabled(true);
        btnOpcion4.setEnabled(true);
        btnOpcion1.setText(preguntaActual.getOpcion1());
        btnOpcion2.setText(preguntaActual.getOpcion2());
        btnOpcion3.setText(preguntaActual.getOpcion3());
        btnOpcion4.setText(preguntaActual.getOpcion4());
    }

    private void boton6() {
        actual = btn6;
        preguntaActual = sortearPregunta();
        puntajeActual = preguntaActual.getPuntaje();
        correcta = preguntaActual.getOpcionCorrecta();
        tvPregunta.setText(preguntaActual.getPregunta());
        btnOpcion1.setEnabled(true);
        btnOpcion2.setEnabled(true);
        btnOpcion3.setEnabled(true);
        btnOpcion4.setEnabled(true);
        btnOpcion1.setText(preguntaActual.getOpcion1());
        btnOpcion2.setText(preguntaActual.getOpcion2());
        btnOpcion3.setText(preguntaActual.getOpcion3());
        btnOpcion4.setText(preguntaActual.getOpcion4());
    }

    private void boton7() {
        actual = btn7;
        preguntaActual = sortearPregunta();
        puntajeActual = preguntaActual.getPuntaje();
        correcta = preguntaActual.getOpcionCorrecta();
        tvPregunta.setText(preguntaActual.getPregunta());
        btnOpcion1.setEnabled(true);
        btnOpcion2.setEnabled(true);
        btnOpcion3.setEnabled(true);
        btnOpcion4.setEnabled(true);
        btnOpcion1.setText(preguntaActual.getOpcion1());
        btnOpcion2.setText(preguntaActual.getOpcion2());
        btnOpcion3.setText(preguntaActual.getOpcion3());
        btnOpcion4.setText(preguntaActual.getOpcion4());
    }

    private void boton8() {
        actual = btn8;
        preguntaActual = sortearPregunta();
        puntajeActual = preguntaActual.getPuntaje();
        correcta = preguntaActual.getOpcionCorrecta();
        tvPregunta.setText(preguntaActual.getPregunta());
        btnOpcion1.setEnabled(true);
        btnOpcion2.setEnabled(true);
        btnOpcion3.setEnabled(true);
        btnOpcion4.setEnabled(true);
        btnOpcion1.setText(preguntaActual.getOpcion1());
        btnOpcion2.setText(preguntaActual.getOpcion2());
        btnOpcion3.setText(preguntaActual.getOpcion3());
        btnOpcion4.setText(preguntaActual.getOpcion4());
    }

    private void boton9() {
        actual = btn9;
        preguntaActual = sortearPregunta();
        puntajeActual = preguntaActual.getPuntaje();
        correcta = preguntaActual.getOpcionCorrecta();
        tvPregunta.setText(preguntaActual.getPregunta());
        btnOpcion1.setEnabled(true);
        btnOpcion2.setEnabled(true);
        btnOpcion3.setEnabled(true);
        btnOpcion4.setEnabled(true);
        btnOpcion1.setText(preguntaActual.getOpcion1());
        btnOpcion2.setText(preguntaActual.getOpcion2());
        btnOpcion3.setText(preguntaActual.getOpcion3());
        btnOpcion4.setText(preguntaActual.getOpcion4());
    }

    private void boton10() {
        actual = btn10;
        preguntaActual = sortearPregunta();
        puntajeActual = preguntaActual.getPuntaje();
        correcta = preguntaActual.getOpcionCorrecta();
        tvPregunta.setText(preguntaActual.getPregunta());
        btnOpcion1.setEnabled(true);
        btnOpcion2.setEnabled(true);
        btnOpcion3.setEnabled(true);
        btnOpcion4.setEnabled(true);
        btnOpcion1.setText(preguntaActual.getOpcion1());
        btnOpcion2.setText(preguntaActual.getOpcion2());
        btnOpcion3.setText(preguntaActual.getOpcion3());
        btnOpcion4.setText(preguntaActual.getOpcion4());
    }

    private void boton11() {
        actual = btn11;
        preguntaActual = sortearPregunta();
        puntajeActual = preguntaActual.getPuntaje();
        correcta = preguntaActual.getOpcionCorrecta();
        tvPregunta.setText(preguntaActual.getPregunta());
        btnOpcion1.setEnabled(true);
        btnOpcion2.setEnabled(true);
        btnOpcion3.setEnabled(true);
        btnOpcion4.setEnabled(true);
        btnOpcion1.setText(preguntaActual.getOpcion1());
        btnOpcion2.setText(preguntaActual.getOpcion2());
        btnOpcion3.setText(preguntaActual.getOpcion3());
        btnOpcion4.setText(preguntaActual.getOpcion4());
    }

    private void boton12() {
        actual = btn12;
        preguntaActual = sortearPregunta();
        puntajeActual = preguntaActual.getPuntaje();
        correcta = preguntaActual.getOpcionCorrecta();
        tvPregunta.setText(preguntaActual.getPregunta());
        btnOpcion1.setEnabled(true);
        btnOpcion2.setEnabled(true);
        btnOpcion3.setEnabled(true);
        btnOpcion4.setEnabled(true);
        btnOpcion1.setText(preguntaActual.getOpcion1());
        btnOpcion2.setText(preguntaActual.getOpcion2());
        btnOpcion3.setText(preguntaActual.getOpcion3());
        btnOpcion4.setText(preguntaActual.getOpcion4());
    }

    private void boton13() {
        actual = btn13;
        preguntaActual = sortearPregunta();
        puntajeActual = preguntaActual.getPuntaje();
        correcta = preguntaActual.getOpcionCorrecta();
        tvPregunta.setText(preguntaActual.getPregunta());
        btnOpcion1.setEnabled(true);
        btnOpcion2.setEnabled(true);
        btnOpcion3.setEnabled(true);
        btnOpcion4.setEnabled(true);
        btnOpcion1.setText(preguntaActual.getOpcion1());
        btnOpcion2.setText(preguntaActual.getOpcion2());
        btnOpcion3.setText(preguntaActual.getOpcion3());
        btnOpcion4.setText(preguntaActual.getOpcion4());
    }

    private void boton14() {
        actual = btn14;
        preguntaActual = sortearPregunta();
        puntajeActual = preguntaActual.getPuntaje();
        correcta = preguntaActual.getOpcionCorrecta();
        tvPregunta.setText(preguntaActual.getPregunta());
        btnOpcion1.setEnabled(true);
        btnOpcion2.setEnabled(true);
        btnOpcion3.setEnabled(true);
        btnOpcion4.setEnabled(true);
        btnOpcion1.setText(preguntaActual.getOpcion1());
        btnOpcion2.setText(preguntaActual.getOpcion2());
        btnOpcion3.setText(preguntaActual.getOpcion3());
        btnOpcion4.setText(preguntaActual.getOpcion4());
    }

    private void boton15() {
        actual = btn15;
        preguntaActual = sortearPregunta();
        puntajeActual = preguntaActual.getPuntaje();
        correcta = preguntaActual.getOpcionCorrecta();
        tvPregunta.setText(preguntaActual.getPregunta());
        btnOpcion1.setEnabled(true);
        btnOpcion2.setEnabled(true);
        btnOpcion3.setEnabled(true);
        btnOpcion4.setEnabled(true);
        btnOpcion1.setText(preguntaActual.getOpcion1());
        btnOpcion2.setText(preguntaActual.getOpcion2());
        btnOpcion3.setText(preguntaActual.getOpcion3());
        btnOpcion4.setText(preguntaActual.getOpcion4());
    }

    private void boton16() {
        actual = btn16;
        preguntaActual = sortearPregunta();
        puntajeActual = preguntaActual.getPuntaje();
        correcta = preguntaActual.getOpcionCorrecta();
        tvPregunta.setText(preguntaActual.getPregunta());
        btnOpcion1.setEnabled(true);
        btnOpcion2.setEnabled(true);
        btnOpcion3.setEnabled(true);
        btnOpcion4.setEnabled(true);
        btnOpcion1.setText(preguntaActual.getOpcion1());
        btnOpcion2.setText(preguntaActual.getOpcion2());
        btnOpcion3.setText(preguntaActual.getOpcion3());
        btnOpcion4.setText(preguntaActual.getOpcion4());
    }

    private void boton17() {
        actual = btn17;
        preguntaActual = sortearPregunta();
        puntajeActual = preguntaActual.getPuntaje();
        correcta = preguntaActual.getOpcionCorrecta();
        tvPregunta.setText(preguntaActual.getPregunta());
        btnOpcion1.setEnabled(true);
        btnOpcion2.setEnabled(true);
        btnOpcion3.setEnabled(true);
        btnOpcion4.setEnabled(true);
        btnOpcion1.setText(preguntaActual.getOpcion1());
        btnOpcion2.setText(preguntaActual.getOpcion2());
        btnOpcion3.setText(preguntaActual.getOpcion3());
        btnOpcion4.setText(preguntaActual.getOpcion4());
    }

    private void boton18() {
        actual = btn18;
        preguntaActual = sortearPregunta();
        puntajeActual = preguntaActual.getPuntaje();
        correcta = preguntaActual.getOpcionCorrecta();
        tvPregunta.setText(preguntaActual.getPregunta());
        btnOpcion1.setEnabled(true);
        btnOpcion2.setEnabled(true);
        btnOpcion3.setEnabled(true);
        btnOpcion4.setEnabled(true);
        btnOpcion1.setText(preguntaActual.getOpcion1());
        btnOpcion2.setText(preguntaActual.getOpcion2());
        btnOpcion3.setText(preguntaActual.getOpcion3());
        btnOpcion4.setText(preguntaActual.getOpcion4());
    }

    private void boton19() {
        actual = btn19;
        preguntaActual = sortearPregunta();
        puntajeActual = preguntaActual.getPuntaje();
        correcta = preguntaActual.getOpcionCorrecta();
        tvPregunta.setText(preguntaActual.getPregunta());
        btnOpcion1.setEnabled(true);
        btnOpcion2.setEnabled(true);
        btnOpcion3.setEnabled(true);
        btnOpcion4.setEnabled(true);
        btnOpcion1.setText(preguntaActual.getOpcion1());
        btnOpcion2.setText(preguntaActual.getOpcion2());
        btnOpcion3.setText(preguntaActual.getOpcion3());
        btnOpcion4.setText(preguntaActual.getOpcion4());
    }

    private void boton20() {
        actual = btn20;
        preguntaActual = sortearPregunta();
        puntajeActual = preguntaActual.getPuntaje();
        correcta = preguntaActual.getOpcionCorrecta();
        tvPregunta.setText(preguntaActual.getPregunta());
        btnOpcion1.setEnabled(true);
        btnOpcion2.setEnabled(true);
        btnOpcion3.setEnabled(true);
        btnOpcion4.setEnabled(true);
        btnOpcion1.setText(preguntaActual.getOpcion1());
        btnOpcion2.setText(preguntaActual.getOpcion2());
        btnOpcion3.setText(preguntaActual.getOpcion3());
        btnOpcion4.setText(preguntaActual.getOpcion4());
    }

    /*
        Se verifica de quien es el turno
     */

    private boolean esTurnoJugador1() {
        return contTurno % 2 == 0;
    }

    private boolean esTurnoJugador2() {
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
        btnOpcion1 = findViewById(R.id.btnOpcion1);
        btnOpcion2 = findViewById(R.id.btnOpcion2);
        btnOpcion3 = findViewById(R.id.btnOpcion3);
        btnOpcion4 = findViewById(R.id.btnOpcion4);
        tvPregunta = findViewById(R.id.tvPregunta);
        tvPuntaje1 = findViewById(R.id.tvPuntaje1);
        tvPuntaje2 = findViewById(R.id.tvPuntaje2);
    }

    /*
        Método que verifica si ya se acabó el juego, retorna verdadero si la lista está vacía, entonces el juego ya habrá terminado
     */

    private ArrayList<Pregunta> listaPreguntas() {
        return archivos.listaPreguntas();
    }

    private boolean revisarVictoria() {
        if (preguntas.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /*
        Retorna el jugador con mayor puntaje, retorna null si hubo un empate
     */

    private Usuario getJugadorGanador(){
        if(jug1.getPuntaje() > jug2.getPuntaje()){
            return jug1;
        }else if(jug2.getPuntaje() > jug1.getPuntaje()){
            return jug2;
        }else{
            return null;
        }
    }

    /*
        Método que retorna una pregunta aleatoria
     */
    private Pregunta sortearPregunta() {

        int posicion = random.nextInt(preguntas.size());
        Pregunta pregunta = preguntas.get(posicion);
        return pregunta;
    }

    /*
        Se verifica si la respuesta escogida por el jugador es la opción correcta de la pregunta
     */
    private boolean respuestaValida() {
        if (preguntaActual.getOpcionCorrecta().equals(respuestaUsuario)) {
            return true;
        } else {
            return false;
        }
    }


    /*
        Intento de bonificaciones
     */
    private boolean bonusDiagonales() {
        if (botones[1] == "correcta" && botones[6] == "correcta" && botones[11] == "correcta" && botones[16] == "correcta") {
            return true;
        } else if (botones[5] == "correcta" && botones[10] == "correcta" && botones[15] == "correcta" && botones[20] == "correcta") {
            return true;
        } else if (botones[4] == "correcta" && botones[7] == "correcta" && botones[10] == "correcta" && botones[13] == "correcta") {
            return true;
        } else if (botones[8] == "correcta" && botones[11] == "correcta" && botones[14] == "correcta" && botones[17] == "correcta") {
            return true;
        }
        return false;

    }

    private boolean bonusHorizontales() {
        if (botones[1] == "correcta" && botones[2] == "correcta" && botones[3] == "correcta" && botones[4] == "correcta") {
            return true;
        } else if (botones[5] == "correcta" && botones[6] == "correcta" && botones[7] == "correcta" && botones[8] == "correcta") {
            return true;
        } else if (botones[9] == "correcta" && botones[10] == "correcta" && botones[11] == "correcta" && botones[12] == "correcta") {
            return true;
        } else if (botones[13] == "correcta" && botones[14] == "correcta" && botones[15] == "correcta" && botones[16] == "correcta") {
            return true;
        } else if (botones[17] == "correcta" && botones[18] == "correcta" && botones[19] == "correcta" && botones[20] == "correcta") {

        }
        return false;

    }

    private boolean bonusVerticales() {
        if (botones[1] == "correcta" && botones[5] == "correcta" && botones[9] == "correcta" && botones[13] == "correcta" && botones[17] == "correcta") {
            return true;
        } else if (botones[2] == "correcta" && botones[6] == "correcta" && botones[10] == "correcta" && botones[14] == "correcta" && botones[18] == "correcta") {
            return true;
        } else if (botones[3] == "correcta" && botones[7] == "correcta" && botones[11] == "correcta" && botones[15] == "correcta" && botones[19] == "correcta") {
            return true;
        } else if (botones[4] == "correcta" && botones[8] == "correcta" && botones[12] == "correcta" && botones[16] == "correcta" && botones[20] == "correcta") {
            return true;
        }
        return false;
    }

    private boolean bonusCuadroExterior() {
        if (botones[1] == "correcta" && botones[5] == "correcta" && botones[9] == "correcta" && botones[13] == "correcta" && botones[17] == "correcta"
                && botones[18] == "correcta" && botones[19] == "correcta" && botones[20] == "correcta" && botones[16] == "correcta" && botones[12] == "correcta"
                && botones[8] == "correcta" && botones[4] == "correcta" && botones[3] == "correcta" && botones[2] == "correcta") {

            return true;
        }
        return false;
    }

    /*
        Retorna el objeto pregunta cuando encuentre la pregunta que es igual a la que se pasa como parámetro, si no se encuentra retorna null
     */
    private Pregunta getPosicionPregunta(ArrayList<Pregunta> preguntas, Pregunta pregunta) {
        for (int i = 0; i < preguntas.size(); i++) {
            if (preguntas.get(i) == pregunta) {
                return preguntas.get(i);
            }
        }
        return null;
    }
}
