package com.gallego.vehiculos.demo.utility;

import com.gallego.vehiculos.demo.dto.CarroDTO;
import com.gallego.vehiculos.demo.entities.Carro;

import java.util.Arrays;
import java.util.List;

public class CarroUtilityTest {


    public static Carro CARRO_UNO = Carro.builder()
            .codigo(1)
            .placa("GXH11A")
            .propietario( PropietarioUtilityTest.PROPIETARIO_UNO )
            .modelo(2004)
            .build();

    public static Carro CARRO_DOS = Carro.builder()
            .codigo(2)
            .placa("GXH11B")
            .propietario( PropietarioUtilityTest.PROPIETARIO_DOS )
            .modelo(2010)
            .build();

    public static CarroDTO CARRODTO_UNO = CarroDTO.builder()
            .codigo(1)
            .placa("GXH11A")
            .propietarioCodigo(1)
            .modelo(2004)
            .build();

    public static CarroDTO CARRODTO_DOS = CarroDTO.builder()
            .codigo(2)
            .placa("GXH11B")
            .propietarioCodigo( 2 )
            .modelo(2010)
            .build();

    public static List<Carro> CARROS = Arrays.asList( CARRO_UNO, CARRO_DOS );
    public static List<CarroDTO> CARROSDTO = Arrays.asList( CARRODTO_UNO, CARRODTO_DOS );
}
