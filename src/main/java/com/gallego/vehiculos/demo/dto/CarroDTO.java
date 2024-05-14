package com.gallego.vehiculos.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class CarroDTO {

    private Integer codigo;
    private Integer modelo;
    private String placa;
    private Integer propietarioCodigo;
}
