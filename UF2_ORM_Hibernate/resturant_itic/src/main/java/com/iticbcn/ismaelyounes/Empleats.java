package com.iticbcn.ismaelyounes;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Empleats")

public class Empleats implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Hibernate manejará el autoincremento del ID
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "telefon", nullable = false, unique = true)
    private String telefon;

    @Column(name = "correo", nullable = false, unique = true)
    private String correo;

    @Column(name = "id_restaurant", nullable = false)
    private String idRestaurant;
}
