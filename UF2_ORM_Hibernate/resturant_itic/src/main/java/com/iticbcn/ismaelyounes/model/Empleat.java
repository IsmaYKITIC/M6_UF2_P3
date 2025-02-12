package com.iticbcn.ismaelyounes.model;

import java.io.Serializable;

public class Empleat implements Serializable {

    private long idEmpleat;
    private String nom;
    private String telefon;
    private String correo;
    private Restaurant restaurant;

    public Empleat() {
    }

    public Empleat(String nom, String telefon, String correo) {
        this.nom = nom;
        this.telefon = telefon;
        this.correo = correo;
    }

    public long getIdEmpleat() {
        return idEmpleat;
    }

    public void setIdEmpleat(long idEmpleat) {
        this.idEmpleat = idEmpleat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}