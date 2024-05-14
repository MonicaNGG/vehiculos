package com.gallego.vehiculos.demo.service;

import com.gallego.vehiculos.demo.dto.PropietarioDTO;
import com.gallego.vehiculos.demo.exception.PropietarioException;

import java.util.List;

public interface IPropietarioService {
    PropietarioDTO crearPropietario(PropietarioDTO propietarioDTO ) throws PropietarioException;
    List<PropietarioDTO> listarPropietarios() throws PropietarioException;
    PropietarioDTO         obtenerPropietarioCodigo( Integer codigo ) throws PropietarioException;
    PropietarioDTO         actualizarPropietario( PropietarioDTO propietarioDTO ) throws PropietarioException;
    String                eliminarPropietario( Integer codigo ) throws PropietarioException;
}
