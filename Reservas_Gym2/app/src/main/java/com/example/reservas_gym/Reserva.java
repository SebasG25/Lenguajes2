package com.example.reservas_gym;

public class Reserva {
    /*
    Atributos de la reserva
     */
    private String Name;
    private String Lastname;
    private String Email;
    private String Date;
    private String Hour;

    /*
    Constructor de la clase
     */
    public Reserva(String name, String lastname, String email, String date, String hour){
        setName(name);
        setLastname(lastname);
        setEmail(email);
        setDate(date);
        setHour(hour);
    }

    public Reserva(){

    }

    /*
    Encapsulamiento de los atributos de la clase
     */

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        this.Lastname = lastname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getReservationInfo()
    {
        return (Name + " " + Lastname + " \n" + "Con ID: " + Email + "\n" + "Fecha de reserva: " + Date + "\n" + "Hora de reserva: " + Hour);
    }

    public String getHour() {
        return Hour;
    }

    public void setHour(String hour) {
        this.Hour = hour;
    }
}
