package com.example.reservas_gym;

public class Usuario {

    private String cedula;
    private String contraseña;
    public Usuario(String cedula, String contraseña)
    {
        this.setCedula(cedula);
        this.setContraseña(contraseña);
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
