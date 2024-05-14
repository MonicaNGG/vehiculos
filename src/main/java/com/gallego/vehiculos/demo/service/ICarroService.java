package com.gallego.vehiculos.demo.service;

import com.gallego.vehiculos.demo.dto.CarroDTO;
import com.gallego.vehiculos.demo.exception.CarroException;

import java.util.List;

public interface ICarroService {

    CarroDTO crearCarro(CarroDTO carroDTO ) throws CarroException;
    List<CarroDTO> listarCarros() throws CarroException;
    CarroDTO       listarCarroCodigo( Integer codigo ) throws CarroException;
    CarroDTO       actualizarCarro( CarroDTO carroDTO ) throws CarroException;
    String        eliminarCarro( Integer Id ) throws CarroException;
}
