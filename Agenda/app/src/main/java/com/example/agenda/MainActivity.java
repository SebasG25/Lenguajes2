package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txtApellido, txtNombre, txtTelefono, txtBuscarTelefono, txtTelefonoIngresado, txtNombreIngresado;
    Button btnAgregar, btnBuscar, btnEliminar, btnActualizar;
    ListView lvLista;
    ArrayList<Persona> Contactos = new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conectar();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Persona persona = new Persona(txtNombre.getText().toString(), txtApellido.getText().toString(), txtTelefono.getText().toString());
                String telefonoIngresado = persona.getTelefono();
                if(!YaExiste(Contactos, telefonoIngresado))
                {
                    Contactos.add(persona);
                    adapter = new ArrayAdapter<Persona>(getApplicationContext(), android.R.layout.simple_list_item_1, Contactos);
                    lvLista.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(), "Contacto Agregado", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Este contacto ya está agregado", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                BuscarContacto(Contactos, txtBuscarTelefono.getText().toString());
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                EliminarContacto(Contactos, txtBuscarTelefono.getText().toString());
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ActualizarContacto(Contactos, txtBuscarTelefono.getText().toString(), txtNombreIngresado.getText().toString(), txtTelefonoIngresado.getText().toString());
            }
        });
    }

    private boolean YaExiste(ArrayList<Persona> Contactos, String telefono)
    {
        boolean existe = false;
        for(int i = 0; i < Contactos.size(); i++){
            if(Contactos.get(i).getTelefono().equals(telefono)){
                existe = true;
            }
        }
        return existe;
    }

    private void BuscarContacto(ArrayList<Persona> Contactos, String telefono){
        for(int i = 0; i < Contactos.size(); i++){
            if(YaExiste(Contactos, telefono)){
                Toast.makeText(getApplicationContext(), "Este número le pertenece a " + Contactos.get(i).getNombre() + " " + Contactos.get(i).getApellido(), Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "El número ingresado no tiene dueño", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void ActualizarContacto(ArrayList<Persona> Contactos, String telefono, String nombreIngresado, String telefonoIngresado){
        for(int i = 0; i < Contactos.size(); i++){
            if(YaExiste(Contactos, telefono)){
                Contactos.get(i).setNombre(nombreIngresado);
                Contactos.get(i).setTelefono(telefonoIngresado);
                Toast.makeText(getApplicationContext(), "Este contacto se ha actualizado con éxito", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "El número ingresado no tiene dueño", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void EliminarContacto(ArrayList<Persona> Contactos, String telefono){
        for(int i = 0; i < Contactos.size(); i++){
            if(YaExiste(Contactos, telefono) && Contactos.size() > 0){
                Contactos.remove(i);
                Toast.makeText(getApplicationContext(), "Contacto eliminado con éxito", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "El número ingresado no tiene dueño", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void conectar() {
        txtApellido = findViewById(R.id.txtApellido);
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtBuscarTelefono = findViewById(R.id.txtTelefonoBuscado);
        txtNombreIngresado = findViewById(R.id.txtNombreIngresado);
        txtTelefonoIngresado = findViewById(R.id.txtTelefonoIngresado);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnBuscar = findViewById(R.id.btnBuscar);
        lvLista = findViewById(R.id.lvLista);
    }
}
