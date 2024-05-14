package com.gallego.vehiculos.demo.controller;

import com.gallego.vehiculos.demo.dto.PropietarioDTO;
import com.gallego.vehiculos.demo.service.IPropietarioService;
import com.gallego.vehiculos.demo.utility.PropietarioUtilityTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PropietarioControllerTest {

    @Autowired
    private PropietarioController propietarioController;

    @MockBean
    private IPropietarioService iPropietarioService;

    @Test
    public void crearPropietarioOK() throws Exception {

        when( iPropietarioService.crearPropietario(any()) ).thenReturn( PropietarioUtilityTest.PROPIETARIODTO_UNO );

        ResponseEntity<PropietarioDTO> response = (ResponseEntity<PropietarioDTO>) propietarioController.crear(PropietarioUtilityTest.PROPIETARIODTO_UNO);

        verify( iPropietarioService ).crearPropietario( eq( PropietarioUtilityTest.PROPIETARIODTO_UNO ));

        assertEquals( PropietarioUtilityTest.PROPIETARIODTO_UNO, response.getBody() );
        assertEquals( response.getStatusCode(), HttpStatus.CREATED );
    }

    @Test
    public void listarPropietariosOK() throws Exception{
        when( iPropietarioService.listarPropietarios() ).thenReturn(PropietarioUtilityTest.PROPIETARIOSDTO);

        ResponseEntity<List<PropietarioDTO>> response = (ResponseEntity<List<PropietarioDTO>>) propietarioController.listar();

        assertEquals( response.getBody().size(), PropietarioUtilityTest.PROPIETARIOSDTO.size() );
        assertEquals( response.getStatusCode(), HttpStatus.OK );
    }

    @Test
    public void listarPropietarioCodigoOK() throws Exception{
        when( iPropietarioService.obtenerPropietarioCodigo( any() ) ).thenReturn(PropietarioUtilityTest.PROPIETARIODTO_UNO);
        ResponseEntity<PropietarioDTO> response = (ResponseEntity<PropietarioDTO>) propietarioController.listarPorCodigo(1);


        assertEquals( response.getBody(), PropietarioUtilityTest.PROPIETARIODTO_UNO );
        assertEquals( response.getStatusCode(), HttpStatus.OK );
    }

    @Test
    public void actualizarPropietarioOK() throws Exception{
        when( iPropietarioService.actualizarPropietario(any()) ).thenReturn( PropietarioUtilityTest.PROPIETARIODTO_UNO );

        ResponseEntity<PropietarioDTO> response = (ResponseEntity<PropietarioDTO>) propietarioController.actualizar(PropietarioUtilityTest.PROPIETARIODTO_UNO);

        verify( iPropietarioService ).actualizarPropietario(eq( PropietarioUtilityTest.PROPIETARIODTO_UNO ));

        assertEquals( response.getBody(), PropietarioUtilityTest.PROPIETARIODTO_UNO );
        assertEquals( response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void eliminarPropietarioOK() throws Exception{
        when( iPropietarioService.eliminarPropietario( any() ) ).thenReturn( String.format("El Propietario con id %s fue eliminado", 1));

        ResponseEntity<?> response = propietarioController.eliminar(1);

        verify( iPropietarioService ).eliminarPropietario(eq( 1) );

        assertEquals( response.getBody(), String.format("El Propietario con id %s fue eliminado", 1) );
        assertEquals( response.getStatusCode(), HttpStatus.OK );
    }

}
