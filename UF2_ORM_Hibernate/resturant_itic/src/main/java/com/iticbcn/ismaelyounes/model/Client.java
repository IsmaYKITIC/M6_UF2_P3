package com.iticbcn.ismaelyounes.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Client")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long idClient;

    @Column(name = "Nom")
    private String nom;

    @Column(name = "Telefon")
    private String telefon;

    @Column(name = "Correo")
    private String correo;

    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Reserva> reservas;

    public void addReserva(Reserva r) {
        if (this.reservas == null) {
            this.reservas = new HashSet<>();
        }
        if (!this.reservas.contains(r)) {
            this.reservas.add(r);
            r.setClient(this);
        }
    }

    public Client() {
    }

    public Client(String nom, String telefon, String correo) {
        this.nom = nom;
        this.telefon = telefon;
        this.correo = correo;
    }

    // Getters y setters
    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long id) {
        this.idClient = id;
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

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }
}
