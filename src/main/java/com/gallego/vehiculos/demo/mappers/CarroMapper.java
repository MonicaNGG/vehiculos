package com.gallego.vehiculos.demo.mappers;


import com.gallego.vehiculos.demo.dto.CarroDTO;
import com.gallego.vehiculos.demo.entities.Carro;

import java.util.List;
import java.util.stream.Collectors;

public class CarroMapper {

    public static CarroDTO entitieToDTO(Carro carro ){
        return CarroDTO.builder()
                .codigo( carro.getCodigo() )
                .modelo( carro.getModelo() )
                .placa( carro.getPlaca() )
                .propietarioCodigo( carro.getPropietario() != null ? carro.getPropietario().getCodigo() : null )
                .build();
    }

    public static List<CarroDTO> listEntitieToDTO( List<Carro> carros ){
        return carros.stream().map( CarroMapper::entitieToDTO ).collect(Collectors.toList());
    }

    public static Carro dtoToEntitie( CarroDTO carroDTO ){
        return Carro.builder()
                .codigo( carroDTO.getCodigo() )
                .modelo( carroDTO.getModelo() )
                .placa( carroDTO.getPlaca()   )
                .build();
    }

    public static List<Carro> listDtoToEntitie( List<CarroDTO> carrosDTO ){
        return carrosDTO.stream().map( CarroMapper::dtoToEntitie ).collect(Collectors.toList());
    }
}



