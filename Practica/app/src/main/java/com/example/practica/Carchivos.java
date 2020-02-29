package com.example.practica;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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

    public boolean buscarCuenta(String correo, String contraseña) {
        String lecturaCorreo = "";
        String lecturaContraseña = "";
        try {
            int cont = 0;
            fis = ctx.openFileInput(Archivo);
            int i = fis.read();
            char caracter = 'a';
            while (i > 0) {
                if (cont % 2 == 0 && i != '\n') {
                    while (i > 0) {
                        caracter = (char) i;
                        lecturaCorreo += caracter;
                        i = fis.read();
                    }
                    if (lecturaCorreo.equals(correo)) {
                        while (i > 0) {
                            cont++;
                            caracter = (char) i;
                            lecturaContraseña += caracter;
                            if (lecturaContraseña.equals(contraseña)) {
                                return true;
                            }
                        }
                    }
                }
                i = fis.read();
                cont++;
            }
        } catch (Exception e) {
            Log.e("", e.getMessage());
        }
        return false;
    }
}
