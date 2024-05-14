package com.gallego.vehiculos.demo.entities;


import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "propietarios")
@Builder
@ToString
public class Propietario {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_propietario", nullable = false, unique = true)
    private Integer codigo;


    @Column(name = "nombre")
    private String nombre;

    @Column(name = "identificacion", length = 6)
    private Long identificacion;
}
