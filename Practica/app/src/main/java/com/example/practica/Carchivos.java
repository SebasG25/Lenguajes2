package com.example.practica;

import android.content.Context;
import android.icu.lang.UScript;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Carchivos {

    String Archivo;
    Context ctx;

    FileOutputStream fos;
    FileInputStream fis;

    public Carchivos(Context ctx, String archivo) {
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

    public String leer() {
        String lectura = "";
        try {
            fis = ctx.openFileInput(Archivo);
            int i = fis.read();
            char caracter = 'a';
            while (i > 0) {
                if (i != '\n') {
                    caracter = (char) i;
                    lectura += caracter;
                }
                if (caracter == '.') {
                    lectura += '\n';
                }
                i = fis.read();
            }
        } catch (Exception e) {
            Log.e("", e.getMessage());
        }
        return lectura;
    }

    public ArrayList<Usuario> listaUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String lectura = "";
        String correo = "";
        String contraseña = "";
        try {
            int cont = 0;
            fis = ctx.openFileInput(Archivo);
            int i = 2;
            char caracter = 'a';
            while (i > 0) {
                i = fis.read();
                caracter = (char) i;
                lectura += caracter;
                if (i == '\n') {
                    if (cont == 0) {
                       correo = lectura.trim();
                    } else if(cont == 1){
                        contraseña = lectura.trim();
                        lista.add(new Usuario(correo, contraseña));
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

    public ArrayList<Pregunta> listaPreguntas() {
        ArrayList<Pregunta> lista = new ArrayList<>();
        String lectura = "";
        String pregunta = "", opcion1 = "", opcion2 = "", opcion3 = "", opcion4 = "", opcionCorrecta;
        int valor = 0;
        try {
            int cont = 0;
            fis = ctx.openFileInput(Archivo);
            int i = 2;
            char caracter = 'a';
            while (i > 0) {
                i = fis.read();
                caracter = (char) i;
                lectura += caracter;
                if (i == '\n') {
                    if (cont == 0) {
                        pregunta = lectura.trim();
                    } else if(cont == 1){
                        opcion1 = lectura.trim();
                    }else if(cont == 2){
                        opcion1 = lectura.trim();
                    }else if(cont == 3){
                        opcion1 = lectura.trim();
                    }else if(cont == 4){
                        opcion1 = lectura.trim();
                    }else if(cont == 5){
                        valor = Integer.parseInt(lectura.trim());
                    }else if(cont == 6){
                        opcionCorrecta = lectura.trim();
                        lista.add(new Pregunta(pregunta, opcion1, opcion2, opcion3, opcion4, valor, opcionCorrecta));
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
