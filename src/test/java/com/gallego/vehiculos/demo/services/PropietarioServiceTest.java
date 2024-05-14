package com.gallego.vehiculos.demo.services;


import com.gallego.vehiculos.demo.dto.PropietarioDTO;
import com.gallego.vehiculos.demo.repository.PropietarioRepository;
import com.gallego.vehiculos.demo.service.implementation.PropietarioService;
import com.gallego.vehiculos.demo.utility.PropietarioUtilityTest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@SpringBootTest
public class PropietarioServiceTest {


    @InjectMocks
    private PropietarioService iPropietarioService;

    @Mock
    private PropietarioRepository propietarioRepository;

    @Test
    public void crearPropietario() throws Exception{
        given( propietarioRepository.existsById( 1 ) ).willReturn( false );
        given( propietarioRepository.save( PropietarioUtilityTest.PROPIETARIO_UNO )).willReturn( PropietarioUtilityTest.PROPIETARIO_UNO);

        PropietarioDTO result = iPropietarioService.crearPropietario( PropietarioUtilityTest.PROPIETARIODTO_UNO);

        assertEquals( 1, result.getCodigo() );
    }

    @Test
    public void listarPropietarios() throws Exception{
        given( propietarioRepository.findAll() ).willReturn(PropietarioUtilityTest.PROPIETARIOS );

        List<PropietarioDTO> result = iPropietarioService.listarPropietarios();

        assertEquals( PropietarioUtilityTest.PROPIETARIOSDTO.size(), result.size() );
        assertEquals( PropietarioUtilityTest.PROPIETARIOSDTO.get(0).getIdentificacion(), result.get(0).getIdentificacion() );
    }

    @Test
    public void listarPropietarioCodigo() throws Exception{
        propietarioRepository.save(PropietarioUtilityTest.PROPIETARIO_UNO );

        given( propietarioRepository.existsById(1)).willReturn(true);
        given( propietarioRepository.findById(PropietarioUtilityTest.PROPIETARIO_UNO.getCodigo()) ).willReturn( Optional.of( PropietarioUtilityTest.PROPIETARIO_UNO) );

        given( propietarioRepository.getReferenceById(1)).willReturn( PropietarioUtilityTest.PROPIETARIO_UNO);

        PropietarioDTO result = iPropietarioService.obtenerPropietarioCodigo( 1 );

        assertEquals(1, result.getCodigo() );
        assertEquals( result, PropietarioUtilityTest.PROPIETARIODTO_UNO );
    }

    @Test
    public void actualizarPropietario() throws Exception{
        given( propietarioRepository.existsById(1)).willReturn(true);
        given( propietarioRepository.findById( PropietarioUtilityTest.PROPIETARIO_UNO.getCodigo() ) ).willReturn(Optional.of(PropietarioUtilityTest.PROPIETARIO_UNO)  );

        given( propietarioRepository.save( PropietarioUtilityTest.PROPIETARIO_UNO )).willReturn( PropietarioUtilityTest.PROPIETARIO_UNO);

        PropietarioDTO result = iPropietarioService.actualizarPropietario( PropietarioUtilityTest.PROPIETARIODTO_UNO );

        assertEquals(PropietarioUtilityTest.PROPIETARIODTO_UNO.getIdentificacion(), result.getIdentificacion());
    }
}
