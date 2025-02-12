package com.iticbcn.ismaelyounes.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "Reserva")
public class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReserva", nullable = false, updatable = false)
    private long idReserva;

    @Column(name = "n_Mesa", nullable = false)
    private int n_Mesa;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fecha_hora;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idClient", foreignKey = @ForeignKey(name = "FK_RE_CL"))
    private Client client;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idRestaurant", referencedColumnName = "idRestaurant", updatable = false)
    private Restaurant restaurant;

    public Reserva(int n_Mesa, LocalDateTime fecha_hora) {
        this.n_Mesa = n_Mesa;
        this.fecha_hora = fecha_hora;

    }

    public Reserva() {
    }

    public long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(long idReserva) {
        this.idReserva = idReserva;
    }

    public int getn_Mesa() {
        return n_Mesa;
    }

    public void setn_Mesa(int n_Mesa) {
        this.n_Mesa = n_Mesa;
    }

    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
