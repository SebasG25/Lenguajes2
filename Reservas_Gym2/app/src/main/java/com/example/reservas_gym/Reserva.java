package com.example.reservas_gym;

public class Reserva {
    /*
    Atributos de la reserva
     */
    private String idReserva;
    private String idEstudiante;
    private String date;
    private String hour;

    /*
    Constructor de la clase
     */
    public Reserva(String idReserva,  String idEstudiante, String date, String hour){
        setIdReserva(idReserva);
        setId(idEstudiante);
        setDate(date);
        setHour(hour);
    }

    /*
    Encapsulamiento de los atributos de la clase
     */

    public String getIdReserva(){
        return idReserva;
    }

    public void setIdReserva(String idReserva){
        this.idReserva = idReserva;
    }

    public String getId() {
        return idEstudiante;
    }

    public void setId(String idEstudiante) {
        this.idEstudiante= idEstudiante;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /*public String getReservationInfo()
    {
        return (name + " " + lastname + " \n" + "Con ID: " + idEstudiante + "\n" + "Fecha de reserva: " + date + "\n" + "Hora de reserva: " + hour);
    }
    */


    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
