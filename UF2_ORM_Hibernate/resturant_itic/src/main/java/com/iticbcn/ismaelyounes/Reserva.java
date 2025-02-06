package com.iticbcn.ismaelyounes;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Reserva")

public class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_mesa")
    private int idMesa;
    @Column(name = "Id_cliente")
    private String idCliente;
    @Column(name = "fecha_hora")
    private LocalDateTime fecha_hora;

    public Reserva(int idMesa, String idCliente, LocalDateTime fecha_hora) {
        this.idCliente = idCliente;
        this.idMesa = idMesa;
        this.fecha_hora = fecha_hora;
    }

}
