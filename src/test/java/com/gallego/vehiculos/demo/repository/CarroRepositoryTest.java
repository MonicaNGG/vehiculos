package com.gallego.vehiculos.demo.repository;

import com.gallego.vehiculos.demo.entities.Carro;
import com.gallego.vehiculos.demo.utility.CarroUtilityTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class CarroRepositoryTest {

    @Mock
    private CarroRepository carroRepository;

    @Test
    public void crearCarroTest(){
        given( carroRepository.save(CarroUtilityTest.CARRO_UNO ) ).willReturn( CarroUtilityTest.CARRO_UNO );

        Carro result = carroRepository.save( CarroUtilityTest.CARRO_UNO );

        assertNotNull( result );
        assertEquals( result.getPlaca(),CarroUtilityTest.CARRO_UNO.getPlaca());
    }

    @Test
    public void obtenerCarrosTest(){
        given( carroRepository.findAll() ).willReturn( CarroUtilityTest.CARROS );

        List<Carro> result = carroRepository.findAll();

        assertNotNull( result );
        assertEquals( result.size(), CarroUtilityTest.CARROS.size() );
    }


    @Test
    public void obtenerCarroPlaca(){
        given( carroRepository.findByPlaca( CarroUtilityTest.CARRO_UNO.getPlaca())).willReturn( Optional.of(CarroUtilityTest.CARRO_UNO) );

        Optional<Carro> result = carroRepository.findByPlaca( CarroUtilityTest.CARRO_UNO.getPlaca());

        assertNotNull( result );
        assertEquals(  result , Optional.of(CarroUtilityTest.CARRO_UNO) );
    }
}
