package com.listasbr.demons.model;

public class User {
    private String nombre;
    private String apellido;
    private String edad;
    private String cedula;
    
    public User() {
    }


    public String getCedula() {
        return cedula;
    }


    public void setCedula(String cedula) {
        this.cedula = cedula;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getApellido() {
        return apellido;
    }


    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getEdad() {
        return edad;
    }


    public void setEdad(String edad) {
        this.edad = edad;
    }

    
}
