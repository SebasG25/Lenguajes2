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
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Carchivos implements Serializable {

    String Archivo;
    Context ctx;
    FileOutputStream fos;
    FileInputStream fis;

    /*
     Constructor que recibe el contexto y el nombre del archivo que se va a abrir
     */
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

    /*
        Lee el archivo plano "Cuentas.txt" y después de leerlo retorna una lista que contiene objetos Usuario
     */
    public ArrayList<Usuario> listaUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String lectura = "";
        String correo = "";
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

    /*
        Lee el archivo plano "Puntajes.txt" y después de leerlo retorna una lista que contiene objetos Puntaje
     */
    public ArrayList<Puntaje> listaPuntajes() {
        ArrayList<Puntaje> lista = new ArrayList<>();
        String lectura = "";
        String nombre = "";
        String puntaje = "";
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
                        nombre = lectura.trim();
                    } else if(cont == 1){
                        puntaje = lectura.trim();
                        lista.add(new Puntaje(nombre, puntaje));
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

    /*
        Lee el archivo plano "Preguntas.txt" y después de leerlo retorna una lista que contiene objetos Pregunta
     */
    public ArrayList<Pregunta> listaPreguntas() {
        ArrayList<Pregunta> lista = new ArrayList<>();
        String lectura = "";
        String pregunta = "", opcion1 = "", opcion2 = "", opcion3 = "", opcion4 = "", opcionCorrecta = "";
        int valor = 0;
        try {
            int cont = 0;
            fis = ctx.openFileInput(Archivo);
            int i = 6;
            char caracter;
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
                        opcion2 = lectura.trim();
                    }else if(cont == 3){
                        opcion3 = lectura.trim();
                    }else if(cont == 4){
                        opcion4 = lectura.trim();
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
