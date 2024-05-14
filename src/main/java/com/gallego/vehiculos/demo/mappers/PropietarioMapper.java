package com.gallego.vehiculos.demo.mappers;

import com.gallego.vehiculos.demo.dto.PropietarioDTO;
import com.gallego.vehiculos.demo.entities.Propietario;

import java.util.List;
import java.util.stream.Collectors;

public class PropietarioMapper {
    public static PropietarioDTO entitieToDTO(Propietario propietario){
        return PropietarioDTO.builder()
                .codigo( propietario.getCodigo() )
                .nombre( propietario.getNombre() )
                .identificacion( propietario.getIdentificacion() )
                .build();
    }

    public static List<PropietarioDTO> listEntitieToDTO(List<Propietario> propietarios ){
        return propietarios.stream().map(PropietarioMapper::entitieToDTO).collect(Collectors.toList());
    }

    public static Propietario dtoToEntitie( PropietarioDTO propietarioDTO ){
        return Propietario.builder()
                .codigo( propietarioDTO.getCodigo() )
                .nombre( propietarioDTO.getNombre() )
                .identificacion( propietarioDTO.getIdentificacion() )
                .build();
    }

    public static List<Propietario> listDtoToEntitie( List<PropietarioDTO> propietarioDTOS ){
        return propietarioDTOS.stream().map(PropietarioMapper::dtoToEntitie).collect(Collectors.toList());
    }
}
