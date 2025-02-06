package com.iticbcn.ismaelyounes;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Client")

public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private String id;
    @Column(name = "Nom")
    private String nom;
    @Column(name = "Telefon")
    private String telefon;
    @Column(name = "Correo")
    private String correo;

    public Client(String nom, String telefon, String correo) {
        this.nom = nom;
        this.telefon = telefon;
        this.correo = correo;
    }

}