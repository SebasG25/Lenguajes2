package com.example.pracbasesdedatos;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ConsultasActivity extends AppCompatActivity {
    Button btnCrud, btnPunto1, btnPunto2, btnPunto3, btnPunto4, btnPunto5, btnPunto6, btnPunto7;
    DbHelper helper;
    ImageButton imgHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        conectar();
        helper = new DbHelper(getApplicationContext(), "BD", null, 1);
        btnCrud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCrud();
            }
        });
        btnPunto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Punto1();
            }
        });
        btnPunto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Punto2();
            }
        });
        btnPunto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Punto3();
            }
        });
        btnPunto4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Punto4();
            }
        });
        btnPunto5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Punto5();
            }
        });
        btnPunto6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Punto6();
            }
        });
        btnPunto7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Punto7();
            }
        });
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHome();
            }
        });
    }

    private void launchCrud(){
        Intent intent = new Intent(this, crud_activity.class);
        startActivity(intent);
    }

    private void Punto1(){
        Intent intent = new Intent(this, ListaActivity.class);
        intent.putExtra("Registros", TotalRegistros());
        startActivity(intent);
    }

    private void Punto2(){
        Intent intent = new Intent(this, ListaActivity.class);
        intent.putExtra("Registros", TotalUnidadesArrendadas());
        startActivity(intent);
    }

    private void Punto3(){
        Intent intent = new Intent(this, ListaActivity.class);
        intent.putExtra("Registros", TotalUnidadesNoArrendadas());
        startActivity(intent);
    }

    private void Punto4(){
        Intent intent = new Intent(this, ListaActivity.class);
        intent.putExtra("Registros", MejorCliente());
        startActivity(intent);
    }

    private void Punto5(){
        Intent intent = new Intent(this, ListaActivity.class);
        intent.putExtra("Registros", ValorPromedio());
        startActivity(intent);
    }

    private void Punto6(){
        openDialog();
    }

    private void Punto7(){
        Intent intent = new Intent(this, ListaActivity.class);
        intent.putExtra("Registros", TipoViviendaComun());
        startActivity(intent);
    }

    private void returnHome(){

        finish();
    }

    private ArrayList<String> TotalRegistros(){
        ArrayList<String> datos = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        String SQL = "Select * from Habitaciones";
        Cursor c = db.rawQuery(SQL, null);
        try{
            if(c.moveToFirst()){
                do{
                    String linea = "Id: " + c.getInt(0) + "\nTipo: " + c.getString(1) + "\nPrecio: " + c.getString(2) + "\nDirección: " + c.getString(3) + "\nNombre: " + c.getString(4) + "\nTeléfono: " + c.getString(5) + "\nFecha Recepción: " + c.getString(6) + "\nArrendada: " + c.getString(7);
                    datos.add(linea);
                }while(c.moveToNext());
            }
            db.close();
        }catch(Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return datos;
    }

    private ArrayList<String> TotalUnidadesArrendadas(){
        ArrayList<String> datos = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        String SQL = "SELECT * FROM Habitaciones WHERE Arrendada = 'SI'";
        Cursor c = db.rawQuery(SQL, null);
        try{
            if(c.moveToFirst()){
                do{
                    String linea = "Id: " + c.getInt(0) + "\nTipo: " + c.getString(1) + "\nPrecio: " + c.getString(2) + "\nDirección: " + c.getString(3) + "\nNombre: " + c.getString(4) + "\nTeléfono: " + c.getString(5) + "\nFecha Recepción: " + c.getString(6) + "\nArrendada: " + c.getString(7);
                    datos.add(linea);
                }while(c.moveToNext());
            }
            db.close();
        }catch(Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return datos;
    }

    private ArrayList<String> TotalUnidadesNoArrendadas(){
        ArrayList<String> datos = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        String SQL = "SELECT * FROM Habitaciones WHERE Arrendada = 'NO'";
        Cursor c = db.rawQuery(SQL, null);
        try{
            if(c.moveToFirst()){
                do{
                    String linea = "Id: " + c.getInt(0) + "\nTipo: " + c.getString(1) + "\nPrecio: " + c.getString(2) + "\nDirección: " + c.getString(3) + "\nNombre: " + c.getString(4) + "\nTeléfono: " + c.getString(5) + "\nFecha Recepción: " + c.getString(6) + "\nArrendada: " + c.getString(7);
                    datos.add(linea);
                }while(c.moveToNext());
            }
            db.close();
        }catch(Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return datos;
    }

    private ArrayList<String> MejorCliente(){
        ArrayList<String> datos = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        String SQL = "SELECT Nombre, Telefono, COUNT(Nombre) AS Total FROM Habitaciones GROUP BY Nombre ORDER BY Total DESC";
        Cursor c = db.rawQuery(SQL, null);
        try{
            if(c.moveToFirst()){
                do{
                    String linea = "Nombre propietario: " + c.getString(0) + "\nTeléfono propietario: " + c.getString(1) +"\nTotal habitaciones arrendadas: " + c.getString(2);
                    datos.add(linea);
                }while(c.moveToNext());
            }
            db.close();
        }catch(Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return datos;
    }

    private ArrayList<String> ValorPromedio(){
        ArrayList<String> datos = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        String SQL = "SELECT AVG(Precio) FROM Habitaciones";
        Cursor c = db.rawQuery(SQL, null);
        try{
            if(c.moveToFirst()){
                do{
                    String linea = "Promedio: " +  c.getString(0);
                    datos.add(linea);
                }while(c.moveToNext());
            }
            db.close();

        }catch(Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return datos;
    }

    private ArrayList<String> UnidadesDiaEspecifico(String fecha){
        ArrayList<String> datos = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        String SQL = "SELECT * FROM Habitaciones WHERE Fecha = '"+fecha+"'";
        Cursor c = db.rawQuery(SQL, null);
        try{
            if(c.moveToFirst()){
                do{
                    String linea = "Id: " + c.getInt(0) + "\nTipo: " + c.getString(1) + "\nPrecio: " + c.getString(2) + "\nDirección: " + c.getString(3) + "\nNombre: " + c.getString(4) + "\nTeléfono: " + c.getString(5) + "\nFecha Recepción: " + c.getString(6) + "\nArrendada: " + c.getString(7);
                    datos.add(linea);
                }while(c.moveToNext());
            }
            db.close();
        }catch(Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return datos;
    }

    private ArrayList<String> TipoViviendaComun(){
        ArrayList<String> datos = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        String SQL = "SELECT Tipo, COUNT(Tipo) AS Total FROM Habitaciones GROUP BY Tipo ORDER BY Total DESC";
        Cursor c = db.rawQuery(SQL, null);
        try{
            if(c.moveToFirst()){
                do{
                    String linea = "Tipo: " +  c.getString(0) + "\nTotal: " + c.getString(1);
                    datos.add(linea);
                }while(c.moveToNext());
            }
            db.close();

        }catch(Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return datos;
    }

    private void openDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(ConsultasActivity.this);
        View view = getLayoutInflater().inflate(R.layout.layout_dialog, null);

        final CalendarView cvFechaEsp = view.findViewById(R.id.cvFechaEsp);
        final TextView txtFechaEsp = view.findViewById(R.id.txtFechaEsp);

        dialog.setIcon(R.drawable.calendar);
        dialog.setView(view);
        dialog.setTitle("Seleccione una fecha específica");
        dialog.setCancelable(false);

        cvFechaEsp.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                txtFechaEsp.setText(dayOfMonth + "/" + "0"+(month + 1) + "/" + year);
            }
        });

        dialog.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String fecha = txtFechaEsp.getText().toString().trim();
                if(!fecha.isEmpty()){
                    Intent intent = new Intent(ConsultasActivity.this, ListaActivity.class);
                    intent.putExtra("Registros", UnidadesDiaEspecifico(fecha));
                    ConsultasActivity.this.startActivity(intent);
                }else{
                    Toast.makeText(ConsultasActivity.this, "Debe ingresar una fecha para continuar", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        final AlertDialog alert = dialog.create();
        alert.show();
    }

    private void conectar(){
        imgHome = findViewById(R.id.imgHome);
        btnCrud = findViewById(R.id.btnCrud);
        btnPunto1 = findViewById(R.id.btnPunto1);
        btnPunto2 = findViewById(R.id.btnPunto2);
        btnPunto3 = findViewById(R.id.btnPunto3);
        btnPunto4 = findViewById(R.id.btnPunto4);
        btnPunto5 = findViewById(R.id.btnPunto5);
        btnPunto6 = findViewById(R.id.btnPunto6);
        btnPunto7 = findViewById(R.id.btnPunto7);
    }
}
