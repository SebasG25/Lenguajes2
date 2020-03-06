package com.example.practica;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Comparator;

public class PuntajeActivity extends AppCompatActivity {
    ListView lvTopJugadores;
    Carchivos archivos;
    ArrayList<String> infoJugadores = new ArrayList<>();
    ArrayList<Puntaje> puntajeJugadores;
    ArrayAdapter adaptador;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
            Se crea el archivo "Puntajes.txt"
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntajes);
        archivos = new Carchivos(this, "Puntajes.txt");
        conectar();
        mostrarJugadores();
    }

    /*
        Se compara los puntajes de la lista para ordenarlos de mayor a menor en un list view
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void mostrarJugadores(){
        puntajeJugadores = listaPuntajes();
        puntajeJugadores.sort(new Comparator<Puntaje>() {
            @Override
            public int compare(Puntaje o1, Puntaje o2) {
                return o2.getPuntaje().compareTo(o1.getPuntaje());
            }
        });
        for(int i = 0; i < puntajeJugadores.size(); i++){
            String nombre = i+1 + ". " + "Nombre: " + puntajeJugadores.get(i).getNombre();
            String puntaje = "Puntaje: " + puntajeJugadores.get(i).getPuntaje();
            String texto = nombre + " " + puntaje;
            infoJugadores.add(texto);
        }
        adaptador = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, infoJugadores);
        lvTopJugadores.setAdapter(adaptador);
    }

    /*
        Retorna la lista que retorna el metodo de la clase de Carchivos después de leer el archivo plano
     */
    private ArrayList<Puntaje> listaPuntajes(){
        return archivos.listaPuntajes();
    }

    private void conectar() {
        lvTopJugadores=findViewById(R.id.topJugadores);
    }
}
