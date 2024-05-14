package com.gallego.vehiculos.demo.repository;

import com.gallego.vehiculos.demo.entities.Propietario;
import com.gallego.vehiculos.demo.utility.PropietarioUtilityTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class PropietarioRepositoryTest {

    @Mock
    PropietarioRepository propietarioRepository;

    @Test
    public void crearPropietarioTest(){
        given( propietarioRepository.save(PropietarioUtilityTest.PROPIETARIO_UNO)).willReturn(PropietarioUtilityTest.PROPIETARIO_UNO);

        Propietario result = propietarioRepository.save( PropietarioUtilityTest.PROPIETARIO_UNO );

        assertNotNull( result );
        assertEquals( result.getIdentificacion(), PropietarioUtilityTest.PROPIETARIO_UNO.getIdentificacion() );
    }

    @Test
    public void obtenerPropietariosTest(){
        given( propietarioRepository.findAll() ).willReturn( PropietarioUtilityTest.PROPIETARIOS );

        List<Propietario> result = propietarioRepository.findAll();

        assertNotNull( result );
        assertEquals( result.size(), PropietarioUtilityTest.PROPIETARIOS.size() );

    }


    @Test
    public void obtenerPropietarioIdentificacion(){
        given( propietarioRepository.findByIdentificacion( PropietarioUtilityTest.PROPIETARIO_UNO.getIdentificacion())).willReturn( PropietarioUtilityTest.PROPIETARIO_UNO );

        Propietario result = propietarioRepository.findByIdentificacion( PropietarioUtilityTest.PROPIETARIO_UNO.getIdentificacion());

        assertNotNull( result );
        assertEquals( result, PropietarioUtilityTest.PROPIETARIO_UNO );
    }

    @Test
    public void obtenerPropietarioPorNombre(){
        given( propietarioRepository.findByNombre( PropietarioUtilityTest.PROPIETARIO_UNO.getNombre())).willReturn(PropietarioUtilityTest.PROPIETARIO_UNO);

        Propietario result = propietarioRepository.findByNombre( PropietarioUtilityTest.PROPIETARIO_UNO.getNombre());
        assertNotNull( result );
        assertEquals( result, PropietarioUtilityTest.PROPIETARIO_UNO );
    }
}
