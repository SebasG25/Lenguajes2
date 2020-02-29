package com.example.practica;

public class Pregunta {

    private Pregunta[] bancoPreguntas;
    private String pregunta;
    private String opcion1;
    private String opcion2;
    private String opcion3;
    private String opcion4;
    private String opcionCorrecta;
    private int puntaje;

    public Pregunta(String pregunta, String opcion1, String opcion2, String opcion3, String opcion4, int puntaje, String opcionCorrecta){
        this.setPregunta(pregunta);
        this.setOpcion1(opcion1);
        this.setOpcion2(opcion2);
        this.setOpcion3(opcion3);
        this.setOpcion4(opcion4);
        this.setOpcionCorrecta(opcionCorrecta);
        this.setPuntaje(puntaje);
    }

    public Pregunta(Pregunta[] banco){
        setBancoPreguntas(banco);
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public String getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(String opcion3) {
        this.opcion3 = opcion3;
    }

    public String getOpcion4() {
        return opcion4;
    }

    public void setOpcion4(String opcion4) {
        this.opcion4 = opcion4;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getOpcionCorrecta() {
        return opcionCorrecta;
    }

    public void setOpcionCorrecta(String opcionCorrecta) {
        this.opcionCorrecta = opcionCorrecta;
    }

    public Pregunta[] getBancoPreguntas() {
        return bancoPreguntas;
    }

    public void setBancoPreguntas(Pregunta[] bancoPreguntas) {
        this.bancoPreguntas = bancoPreguntas;
    }
}
