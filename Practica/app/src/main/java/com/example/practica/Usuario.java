package com.example.practica;

import java.util.ArrayList;

public class Usuario {

    private  String nombre;
    private String contraseña;

    public Usuario(String nombre, String contraseña) {
        this.setNombre(nombre);
        this.setContraseña(contraseña);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
