package com.iticbcn.ismaelyounes.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Restaurant")
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRestaurant;

    @Column(name = "Nom", nullable = false)
    private String nom;

    @Column(name = "Capacitat", nullable = false)
    private int capacitat;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.PERSIST)
    private Set<Empleat> empleats = new HashSet<>();

    public void addEmpleat(Empleat e) {

        if (!this.empleats.contains(e)) {
            this.empleats.add(e);
            e.setRestaurant(this);
        }
    }

    public Restaurant(String nom, int capacitat) {
        this.nom = nom;
        this.capacitat = capacitat;

    }

    public Restaurant() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCapacitat() {
        return capacitat;
    }

    public void setCapacitat(int capacitat) {
        this.capacitat = capacitat;
    }

    public Set<Empleat> getEmpleats() {
        return empleats;
    }

    public void setEmpleats(Set<Empleat> empleats) {
        this.empleats = empleats;
    }

    public long getIdRestaurant() {
        return idRestaurant;
    }

    public void setId_Restaurant(long id_Restaurant) {
        this.idRestaurant = id_Restaurant;
    }
}