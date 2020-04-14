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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    int contArriendo = 0;
    DbHelper helper;
    ImageButton imgHome;
    Button btnTipo, btnPrecio, btnDireccion, btnNombre, btnTelefono, btnFecha, btnArriendo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        conectar();
        helper = new DbHelper(getApplicationContext(), "BD", null, 1);
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHome();
            }
        });
        btnTipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActualizarTipoDialog();
            }
        });
        btnPrecio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActualizarPrecioDialog();
            }
        });
        btnDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openActualizarDireccionDialog();
            }
        });
        btnNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActualizarNombreDialog();
            }
        });
        btnTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActualizarTelefonoDialog();
            }
        });
        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActualizarFechaDialog();
            }
        });
        btnArriendo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActualizarArriendoDialog();
            }
        });
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

    private ArrayList<String> UpdateTipo(String id, String nuevoTipo){
        SQLiteDatabase db = helper.getWritableDatabase();
        String SQL = "Update Habitaciones set Tipo = '"+nuevoTipo+"' where Id = '"+id+"'";
        try {
            db.execSQL(SQL);
            db.close();
        }catch(Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return leerBD();
    }

    private ArrayList<String> UpdatePrecio(String id, double nuevoPrecio){
        SQLiteDatabase db = helper.getWritableDatabase();
        String SQL = "Update Habitaciones set Precio = "+nuevoPrecio+" where Id = '"+id+"'";
        try {
            db.execSQL(SQL);
            db.close();
        }catch(Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return leerBD();
    }

    private ArrayList<String> UpdateDireccion(String id, String nuevaDireccion){
        SQLiteDatabase db = helper.getWritableDatabase();
        String SQL = "Update Habitaciones set Direccion = '"+nuevaDireccion+"' where Id = '"+id+"'";
        try {
            db.execSQL(SQL);
            db.close();
        }catch(Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return leerBD();
    }

    private ArrayList<String> UpdateNombre(String id, String nuevoNombre){
        SQLiteDatabase db = helper.getWritableDatabase();
        String SQL = "Update Habitaciones set Nombre = '"+nuevoNombre+"' where Id = '"+id+"'";
        try {
            db.execSQL(SQL);
            db.close();
        }catch(Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return leerBD();
    }

    private ArrayList<String> UpdateTelefono(String id, String nuevoTelefono){
        SQLiteDatabase db = helper.getWritableDatabase();
        String SQL = "Update Habitaciones set Telefono = '"+nuevoTelefono+"' where Id = '"+id+"'";
        try {
            db.execSQL(SQL);
            db.close();
        }catch(Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return leerBD();
    }

    private ArrayList<String> UpdateFecha(String id, String nuevaFecha){
        SQLiteDatabase db = helper.getWritableDatabase();
        String SQL = "Update Habitaciones set Fecha = '"+nuevaFecha+"' where Id = '"+id+"'";
        try {
            db.execSQL(SQL);
            db.close();
        }catch(Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return leerBD();
    }

    private ArrayList<String> UpdateArriendo(String id, String nuevoArriendo){
        SQLiteDatabase db = helper.getWritableDatabase();
        String SQL = "Update Habitaciones set Arrendada = '"+nuevoArriendo+"' where Id = '"+id+"'";
        try {
            db.execSQL(SQL);
            db.close();
        }catch(Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return leerBD();
    }

    private void openActualizarTipoDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(UpdateActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.layout_dialog_update, null);



        final EditText txtId= mView.findViewById(R.id.txtId);
        final ListView lvHabitaciones = mView.findViewById(R.id.lvHabitaciones);
        final EditText txtNuevo = mView.findViewById(R.id.txtNuevo);

        txtNuevo.setHint("Ingrese nuevo tipo (casa, apto, finca)");

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

        dialog.setTitle("Actualizar tipo");
        dialog.setView(mView);
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
                String tipo = txtNuevo.getText().toString().trim();
                if(!id.isEmpty() && !tipo.isEmpty()){
                    Intent intent = new Intent(UpdateActivity.this, ListaActivity.class);
                    intent.putExtra("Registros", UpdateTipo(id, tipo));
                    UpdateActivity.this.startActivity(intent);
                }else if(!(tipo.equals("casa")||tipo.equals("apto")||tipo.equals("finca"))) {
                    Toast.makeText(UpdateActivity.this, "Debe ingresar casa, apto o finca", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(UpdateActivity.this, "Debe ingresar un Id y tipo para continuar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final AlertDialog alert = dialog.create();
        alert.show();
    }

    private void openActualizarPrecioDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(UpdateActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.layout_dialog_update, null);



        final EditText txtId= mView.findViewById(R.id.txtId);
        final ListView lvHabitaciones = mView.findViewById(R.id.lvHabitaciones);
        final EditText txtNuevo = mView.findViewById(R.id.txtNuevo);

        txtNuevo.setHint("Ingrese nuevo precio");

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

        dialog.setTitle("Actualizar precio");
        dialog.setView(mView);
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
                String precio = txtNuevo.getText().toString().trim();
                if(!id.isEmpty() && !precio.isEmpty()){
                    Intent intent = new Intent(UpdateActivity.this, ListaActivity.class);
                    intent.putExtra("Registros", UpdatePrecio(id, Double.parseDouble(precio)));
                    UpdateActivity.this.startActivity(intent);
                }else{
                    Toast.makeText(UpdateActivity.this, "Debe ingresar un Id y precio para continuar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final AlertDialog alert = dialog.create();
        alert.show();
    }

    private void openActualizarDireccionDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(UpdateActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.layout_dialog_update, null);



        final EditText txtId= mView.findViewById(R.id.txtId);
        final ListView lvHabitaciones = mView.findViewById(R.id.lvHabitaciones);
        final EditText txtNuevo = mView.findViewById(R.id.txtNuevo);

        txtNuevo.setHint("Ingrese nueva dirección");

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

        dialog.setTitle("Actualizar dirección");
        dialog.setView(mView);
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
                String direccion = txtNuevo.getText().toString().trim();
                if(!id.isEmpty() && !direccion.isEmpty()){
                    Intent intent = new Intent(UpdateActivity.this, ListaActivity.class);
                    intent.putExtra("Registros", UpdateDireccion(id, direccion));
                    UpdateActivity.this.startActivity(intent);
                }else{
                    Toast.makeText(UpdateActivity.this, "Debe ingresar un Id y dirección para continuar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final AlertDialog alert = dialog.create();
        alert.show();
    }

    private void openActualizarNombreDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(UpdateActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.layout_dialog_update, null);



        final EditText txtId= mView.findViewById(R.id.txtId);
        final ListView lvHabitaciones = mView.findViewById(R.id.lvHabitaciones);
        final EditText txtNuevo = mView.findViewById(R.id.txtNuevo);

        txtNuevo.setHint("Ingrese nuevo nombre");

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

        dialog.setTitle("Actualizar nombre");
        dialog.setView(mView);
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
                String nombre = txtNuevo.getText().toString().trim();
                if(!id.isEmpty() && !nombre.isEmpty()){
                    Intent intent = new Intent(UpdateActivity.this, ListaActivity.class);
                    intent.putExtra("Registros", UpdateNombre(id, nombre));
                    UpdateActivity.this.startActivity(intent);
                }else{
                    Toast.makeText(UpdateActivity.this, "Debe ingresar un Id y nombre para continuar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final AlertDialog alert = dialog.create();
        alert.show();
    }

    private void openActualizarTelefonoDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(UpdateActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.layout_dialog_update, null);



        final EditText txtId= mView.findViewById(R.id.txtId);
        final ListView lvHabitaciones = mView.findViewById(R.id.lvHabitaciones);
        final EditText txtNuevo = mView.findViewById(R.id.txtNuevo);

        txtNuevo.setHint("Ingrese nuevo teléfono");

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

        dialog.setTitle("Actualizar teléfono");
        dialog.setView(mView);
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
                String telefono = txtNuevo.getText().toString().trim();
                if(!id.isEmpty() && !telefono.isEmpty()){
                    Intent intent = new Intent(UpdateActivity.this, ListaActivity.class);
                    intent.putExtra("Registros", UpdateTelefono(id, telefono));
                    UpdateActivity.this.startActivity(intent);
                }else{
                    Toast.makeText(UpdateActivity.this, "Debe ingresar un Id y teléfono para continuar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final AlertDialog alert = dialog.create();
        alert.show();
    }

    private void openActualizarFechaDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(UpdateActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.layout_dialog_update_fecha, null);



        final EditText txtId= mView.findViewById(R.id.txtId);
        final EditText txtFechaEsp= mView.findViewById(R.id.txtFechaEsp);
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

        dialog.setTitle("Actualizar fecha");
        dialog.setView(mView);
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
                String fechaEsp = txtFechaEsp.getText().toString().trim();
                if(!id.isEmpty() && !fechaEsp.isEmpty()){
                    Intent intent = new Intent(UpdateActivity.this, ListaActivity.class);
                    intent.putExtra("Registros", UpdateFecha(id, fechaEsp));
                    UpdateActivity.this.startActivity(intent);
                }else{
                    Toast.makeText(UpdateActivity.this, "Debe ingresar un Id y fecha para continuar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final AlertDialog alert = dialog.create();
        alert.show();
    }

    private void estaArrendada(TextView tvRespuestaArr){
        contArriendo++;
        if(contArriendo %2 == 1){
            tvRespuestaArr.setText("SI");
        }else if(contArriendo % 2 == 0){
            tvRespuestaArr.setText("NO");
        }
    }

    private void openActualizarArriendoDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(UpdateActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.layout_dialog_update_arriendo, null);


        final Switch swcArriendo = mView.findViewById(R.id.swchArrendada);
        final EditText txtId= mView.findViewById(R.id.txtId);
        final ListView lvHabitaciones = mView.findViewById(R.id.lvHabitaciones);
        final TextView tvArriendo = mView.findViewById(R.id.tvRespuestaArriendo);

        swcArriendo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estaArrendada(tvArriendo);
            }
        });

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

        dialog.setTitle("Actualizar arriendo");
        dialog.setView(mView);
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
                String respuestaArriendo = tvArriendo.getText().toString().trim();
                if(!id.isEmpty()){
                    Intent intent = new Intent(UpdateActivity.this, ListaActivity.class);
                    intent.putExtra("Registros", UpdateArriendo(id, respuestaArriendo));
                    UpdateActivity.this.startActivity(intent);
                }else{
                    Toast.makeText(UpdateActivity.this, "Debe ingresar un Id para continuar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final AlertDialog alert = dialog.create();
        alert.show();
    }

    private void returnHome(){
        finish();
    }

    private void conectar() {
        imgHome = findViewById(R.id.imgHome);
        btnTipo = findViewById(R.id.btnTipo);
        btnPrecio = findViewById(R.id.btnPrecio);
        btnDireccion = findViewById(R.id.btnDireccion);
        btnNombre = findViewById(R.id.btnNombre);
        btnTelefono = findViewById(R.id.btnTelefono);
        btnFecha = findViewById(R.id.btnFecha);
        btnArriendo = findViewById(R.id.btnArriendo);
    }
}
