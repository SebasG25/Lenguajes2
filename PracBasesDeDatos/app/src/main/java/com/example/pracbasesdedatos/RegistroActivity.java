package com.example.pracbasesdedatos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

    int contArriendo = 0;
    EditText txtTipo, txtPrecio, txtDireccion, txtNombreProp, txtTelefonoProp, txtFechaRecep;
    Switch swchEstaArreandada;
    TextView tvRespuestaArr;
    Button btnRegistrar, btnReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        conectar();
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Return();
            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registro();
            }
        });
        swchEstaArreandada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estaArrendada();
            }
        });
    }

    private void Return(){
        finish();
    }

    private void estaArrendada(){
        contArriendo++;
        if(contArriendo %2 == 1){
            tvRespuestaArr.setText("SI");
        }else if(contArriendo % 2 == 0){
            tvRespuestaArr.setText("NO");
        }
    }

    private void Registro(){

        String tipo = txtTipo.getText().toString().trim();
        String precio = txtPrecio.getText().toString().trim();
        String direccion = txtDireccion.getText().toString().trim();
        String nombreProp = txtNombreProp.getText().toString().trim();
        String telefonoProp = txtTelefonoProp.getText().toString().trim();
        String fechaRecep = txtFechaRecep.getText().toString().trim();
        String respuestaArr = tvRespuestaArr.getText().toString().trim();

        //int id;
        //id = rgArrendada.getCheckedRadioButtonId();
        //rbSelected = findViewById(id);
        //String arredanda = rbSelected.getText().toString().trim();

        if(verificarCampos(tipo, precio, direccion, nombreProp, telefonoProp, fechaRecep, respuestaArr)){
            GuardarInsert(tipo, Double.parseDouble(precio), direccion, nombreProp, telefonoProp, fechaRecep, respuestaArr);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private boolean verificarCampos(String t, String p, String d, String np, String tp, String fr, String arr){
        if(t.isEmpty() || p.isEmpty() || d.isEmpty() || np.isEmpty() || tp.isEmpty() || fr.isEmpty() || arr.isEmpty()){
            Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void Guardar(String tipo, double precio, String direccion, String nombreProp, String telefonoProp, String fechaRecep, String respuestaArr){
        DbHelper helper = new DbHelper(this, "BD", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues cv = new ContentValues();
            cv.put("Tipo", tipo);
            cv.put("Precio", precio);
            cv.put("Direccion", direccion);
            cv.put("Nombre", nombreProp);
            cv.put("Telefono", telefonoProp);
            cv.put("Fecha", fechaRecep);
            cv.put("Arrendada", respuestaArr);
            db.insert("Habitaciones", null, cv);
            db.close();
            Toast.makeText(this, "Registro insertado", Toast.LENGTH_LONG).show();
        }catch(Exception ex){
            Toast.makeText(this, "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void GuardarInsert(String tipo, double precio, String direccion, String nombreProp, String telefonoProp, String fechaRecep, String arrendada){
        DbHelper helper = new DbHelper(this, "BD", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();

        try{
            String insert = "INSERT INTO Habitaciones(Tipo, Precio, Direccion, Nombre, Telefono, Fecha, Arrendada) VALUES('"+tipo+"', "+precio+", '"+direccion+"', '"+nombreProp+"', '"+telefonoProp+"', '"+fechaRecep+"', '"+arrendada+"')";

            db.execSQL(insert);
            db.close();

            Toast.makeText(this, "Registro insertado", Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            Toast.makeText(this, "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void conectar() {
        txtTipo = findViewById(R.id.txtTipo);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtNombreProp = findViewById(R.id.txtNombreProp);
        txtTelefonoProp = findViewById(R.id.txtTelefonoProp);
        txtFechaRecep = findViewById(R.id.txtFechaRecep);
        tvRespuestaArr = findViewById(R.id.tvRespuestaArriendo);
        swchEstaArreandada = findViewById(R.id.swchArrendada);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnReturn = findViewById(R.id.return_arrow);
    }


}
