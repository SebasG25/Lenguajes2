package com.example.pracbasesdedatos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class crud_activity extends AppCompatActivity {

    DbHelper helper;
    ImageButton imgHome;
    Button btnLeer, btnActualizar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        conectar();
        helper = new DbHelper(getApplicationContext(), "BD", null, 1);
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHome();
            }
        });
        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Leer();
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar();
            }
        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Actualizar();
            }
        });
    }

    private void Leer(){
        Intent intent = new Intent(this, ListaActivity.class);
        intent.putExtra("Registros", leerBD());
        startActivity(intent);
    }

    private void Eliminar(){
        openEliminarDialog();
    }

    private void Actualizar(){
        Intent intent = new Intent(this, UpdateActivity.class);
        startActivity(intent);
    }

    private ArrayList<String> leerBD(){
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

    private void openEliminarDialog(){

        AlertDialog.Builder dialog = new AlertDialog.Builder(crud_activity.this);
        View mView = getLayoutInflater().inflate(R.layout.layout_dialog_listview, null);

        final EditText txtId= mView.findViewById(R.id.txtId);
        final ListView lvHabitaciones = mView.findViewById(R.id.lvHabitaciones);

        ArrayList<String> datos = leerBD();
        if(datos.isEmpty()){
            datos.add("No hay datos disponibles para mostrar");
            txtId.setEnabled(false);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listview_item_design, datos){
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                    View view = super.getView(position, convertView, parent);
                    if (position % 2 == 1) {
                        view.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
                    } else {
                        view.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                    }
                    return view;
                }
        };
        lvHabitaciones.setAdapter(adapter);

        dialog.setView(mView);
        dialog.setTitle("Registros");
        dialog.setCancelable(false);

        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String id = txtId.getText().toString().trim();
                if(!id.isEmpty()){
                    Intent intent = new Intent(crud_activity.this, ListaActivity.class);
                    intent.putExtra("Registros", eliminarBD(id));
                    crud_activity.this.startActivity(intent);
                }else{
                    Toast.makeText(crud_activity.this, "Debe ingresar un Id para continuar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final AlertDialog alert = dialog.create();
        alert.show();
    }

    private ArrayList<String> eliminarBD(String id){
        SQLiteDatabase db = helper.getWritableDatabase();
        String SQL = "DELETE FROM Habitaciones WHERE Id = '"+id+"'";
        db.execSQL(SQL);
        db.close();
        return leerBD();
    }

    private void returnHome(){
        finish();
    }

    private void conectar() {
        imgHome = findViewById(R.id.imgHome);
        btnLeer = findViewById(R.id.btnLeer);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);
    }
}
