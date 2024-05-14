package com.gallego.vehiculos.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carros")
@Builder
@ToString
public class Carro {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_carro", nullable = false, unique = true)
    private Integer codigo;

    @Column(name="modelo")
    private Integer modelo;

    @Column(name="placa", length = 6)
    private String placa;

    @ManyToOne
    @JoinColumn(name="codigo_propietario")
    private Propietario propietario;
}
