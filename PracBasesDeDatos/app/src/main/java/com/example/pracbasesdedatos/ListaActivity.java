package com.example.pracbasesdedatos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    ListView lvHabitaciones;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        conectar();
        Cargar();
    }

    private void Cargar(){
        ArrayList<String> datos;
        Bundle recup = getIntent().getExtras();

        if(recup != null){
            datos = recup.getStringArrayList("Registros");
            if(datos.isEmpty()){
                datos.add("No hay datos disponibles para mostrar");
            }
            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview_item_design, datos){
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                    View view = super.getView(position, convertView, parent);
                    if(position % 2 == 1){
                        view.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
                    }else{
                        view.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                    }
                    return view;
                }
            };
            lvHabitaciones.setAdapter(adapter);
        }

    }

    private void conectar() {
        lvHabitaciones = findViewById(R.id.lvHabitaciones);
    }
}
