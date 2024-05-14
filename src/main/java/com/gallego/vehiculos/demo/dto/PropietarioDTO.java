package com.gallego.vehiculos.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class PropietarioDTO {

    private Integer codigo;
    private String nombre;
    private Long identificacion;
}
