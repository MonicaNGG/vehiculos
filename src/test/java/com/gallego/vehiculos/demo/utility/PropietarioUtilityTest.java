package com.gallego.vehiculos.demo.utility;

import com.gallego.vehiculos.demo.dto.PropietarioDTO;
import com.gallego.vehiculos.demo.entities.Propietario;

import java.util.Arrays;
import java.util.List;

public class PropietarioUtilityTest {

    public static Propietario PROPIETARIO_UNO = Propietario.builder()
            .codigo(1)
            .nombre("Monica")
            .identificacion(111111111L)
            .build();

    public static Propietario PROPIETARIO_DOS = Propietario.builder()
            .codigo(2)
            .nombre("Gallego")
            .identificacion(111111112L)
            .build();

    public static List<Propietario> PROPIETARIOS = Arrays.asList( PROPIETARIO_UNO, PROPIETARIO_DOS );


    public static PropietarioDTO PROPIETARIODTO_UNO = PropietarioDTO.builder()
            .codigo(1)
            .nombre("Monica")
            .identificacion(111111111L)
            .build();

    public static PropietarioDTO PROPIETARIODTO_DOS = PropietarioDTO.builder()
            .codigo(2)
            .nombre("Gallego")
            .identificacion(111111112L)
            .build();

    public static List<PropietarioDTO> PROPIETARIOSDTO = Arrays.asList( PROPIETARIODTO_UNO, PROPIETARIODTO_DOS );
}
