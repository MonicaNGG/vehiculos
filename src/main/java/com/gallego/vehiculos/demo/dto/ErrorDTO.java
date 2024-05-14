package com.gallego.vehiculos.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {
    String mensaje;
}
