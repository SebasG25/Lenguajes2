package com.example.practica;

public class Usuario {

    /*
        Atributos del usuario
     */
    private  String nombre;
    private String contraseña;
    private int puntaje;

    /*
        Se crea un Usuario con un nombre y contraseña dada y se inicializa el puntaje en 0
     */
    public Usuario(String nombre, String contraseña) {
        this.setNombre(nombre);
        this.setContraseña(contraseña);
        setPuntaje(0);
    }

    /*
        Se crea un Usuario con un nombre dado y se inicializa el puntaje en 0
     */

    public Usuario(String nombre){
        this.setNombre(nombre);
        setPuntaje(0);
    }

    /*
        Encapsulamiento de los atributos
     */
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

    public int getPuntaje() {
        return puntaje;
    }

    /*
        Se acumula el puntaje dado con el que ya se tenía
     */
    public void setPuntaje(int puntaje) {
        this.puntaje += puntaje;
    }
}
