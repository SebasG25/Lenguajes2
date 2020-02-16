package com.example.agenda;

public class Persona {

    private String nombre;
    private String apellido;
    private String telefono;

    public Persona(String nombre, String apellido, String telefono){
        this.setNombre(nombre);
        this.setTelefono(telefono);
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
