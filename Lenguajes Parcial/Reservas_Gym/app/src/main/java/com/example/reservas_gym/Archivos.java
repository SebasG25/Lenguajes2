package com.example.reservas_gym;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Archivos {
    String Archivo;
    Context ctx;
    FileOutputStream fos;
    FileInputStream fis;

    /*
     Constructor que recibe el contexto y el nombre del archivo que se va a abrir
     */
    public Archivos(Context ctx, String archivo) {
        this.ctx = ctx;
        this.Archivo = archivo;
    }

    public void escribir(String textArchivo) throws IOException {
        try {
            fos = ctx.openFileOutput(Archivo, Context.MODE_APPEND);
            fos.write(textArchivo.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("", e.getMessage());
        } catch (IOException ex) {
            Log.e("", ex.getMessage());
        }
    }

    public ArrayList<Usuario> listaUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String lectura = "";
        String cedula = "";
        String contraseña = "";
        try {
            int cont = 0;
            fis = ctx.openFileInput(Archivo);
            int i = 2;
            char caracter;
            while (i > 0) {
                i = fis.read();
                caracter = (char) i;
                lectura += caracter;
                if (i == '\n') {
                    if (cont == 0) {
                        cedula = lectura.trim();
                    } else if(cont == 1){
                        contraseña = lectura.trim();
                        lista.add(new Usuario(cedula, contraseña));
                        cont =-1;
                    }
                    lectura = "";
                    cont++;
                }
            }
        } catch (Exception e) {
            Log.e("", e.getMessage());
        }
        return lista;
    }
}
