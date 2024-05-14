package com.gallego.vehiculos.demo.services;

import com.gallego.vehiculos.demo.dto.CarroDTO;
import com.gallego.vehiculos.demo.repository.CarroRepository;
import com.gallego.vehiculos.demo.repository.PropietarioRepository;
import com.gallego.vehiculos.demo.service.implementation.CarroService;
import com.gallego.vehiculos.demo.utility.CarroUtilityTest;
import com.gallego.vehiculos.demo.utility.PropietarioUtilityTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class CarroServiceTest {

    @InjectMocks
    private CarroService carroServiceImpl;

    @Mock
    private CarroRepository carroRepository;

    @Mock
    private PropietarioRepository propietarioRepository;

    @Test
    public void crearCarroTest() throws Exception{

        given( propietarioRepository.findById(CarroUtilityTest.CARRODTO_UNO.getPropietarioCodigo()) ).willReturn(Optional.of(PropietarioUtilityTest.PROPIETARIO_UNO));
        given( propietarioRepository.getReferenceById( CarroUtilityTest.CARRODTO_UNO.getPropietarioCodigo() )).willReturn( PropietarioUtilityTest.PROPIETARIO_UNO );

        given( carroRepository.existsById( CarroUtilityTest.CARRODTO_UNO.getCodigo() ) ).willReturn( true );
        given( carroRepository.findByPlaca( CarroUtilityTest.CARRODTO_UNO.getPlaca())).willReturn( Optional.empty() );

        given( carroRepository.save(CarroUtilityTest.CARRO_UNO)).willReturn(CarroUtilityTest.CARRO_UNO);

        CarroDTO result = carroServiceImpl.crearCarro( CarroUtilityTest.CARRODTO_UNO);

        assertEquals( 1, result.getCodigo() );
    }

    @Test
    public void listarCarros() throws Exception{
        given( carroRepository.findAll() ).willReturn( CarroUtilityTest.CARROS );

        List<CarroDTO> result = carroServiceImpl.listarCarros();

        assertEquals( CarroUtilityTest.CARROS.size(), result.size() );
        assertEquals( CarroUtilityTest.CARROSDTO.get(0).getCodigo(), result.get(0).getCodigo() );
    }

    @Test
    public void listarCarroCodigo() throws Exception{
        carroRepository.save(CarroUtilityTest.CARRO_UNO );

        given( carroRepository.existsById(1)).willReturn(true);
        given( carroRepository.findById(CarroUtilityTest.CARRO_UNO.getCodigo()) ).willReturn( Optional.of( CarroUtilityTest.CARRO_UNO) );

        given( carroRepository.getReferenceById(1)).willReturn( CarroUtilityTest.CARRO_UNO );

        CarroDTO result = carroServiceImpl.listarCarroCodigo( 1 );

        assertEquals(1, result.getCodigo() );
        assertEquals( result, CarroUtilityTest.CARRODTO_UNO );
    }

    @Test
    public void actualizarCarro() throws Exception{
        given( carroRepository.existsById(1)).willReturn(true);
        given( carroRepository.findById( CarroUtilityTest.CARRO_UNO.getCodigo() ) ).willReturn(Optional.of(CarroUtilityTest.CARRO_UNO)  );
        given( propietarioRepository.findById(CarroUtilityTest.CARRODTO_UNO.getPropietarioCodigo())).willReturn(Optional.of(PropietarioUtilityTest.PROPIETARIO_UNO));
        given( propietarioRepository.getReferenceById( CarroUtilityTest.CARRODTO_UNO.getPropietarioCodigo() )).willReturn( PropietarioUtilityTest.PROPIETARIO_UNO );


        given( carroRepository.save( CarroUtilityTest.CARRO_UNO )).willReturn( CarroUtilityTest.CARRO_UNO);

        CarroDTO result = carroServiceImpl.actualizarCarro( CarroUtilityTest.CARRODTO_UNO );

        assertEquals(CarroUtilityTest.CARRODTO_UNO.getCodigo(), result.getCodigo() );
    }



}
