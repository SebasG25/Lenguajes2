package com.example.practica;

public class Puntaje {
    /*
        Atributos de la clase Puntaje
     */
    private String nombre;
    private String puntaje;

    /*
        Se crea un objeto Puntaje con un nombre del jugador y su puntaje respectivo
     */
    public Puntaje(String nombre, String puntaje){
        this.setNombre(nombre);
        this.setPuntaje(puntaje);
    }

    /*
        Encapsulamiento de los atributos de la clase Usuario
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(String puntaje) {
        this.puntaje = puntaje;
    }
}
