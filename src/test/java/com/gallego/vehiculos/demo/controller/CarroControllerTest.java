package com.gallego.vehiculos.demo.controller;

import com.gallego.vehiculos.demo.dto.CarroDTO;
import com.gallego.vehiculos.demo.service.ICarroService;
import com.gallego.vehiculos.demo.utility.CarroUtilityTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;



@SpringBootTest
public class CarroControllerTest {

    //@Autowired se utiliza para inyectar una instancia real del controlador carroController.
    //@MockBean se utiliza para crear un mock del servicio iCarroService. Simular sin utilizar el real
    @Autowired
    private CarroController carroController;

    @MockBean
    private ICarroService iCarroService;

    @Test
    public void crearCarroOK() throws Exception {

        //Este código configura el comportamiento esperado del mock del servicio iCarroService. Indica que cuando se llame al método crearCarro
        // del servicio con cualquier objeto CarroDTO como argumento,
        // debe devolver el objeto CarroDTO definido en CarroUtilityTest.CARRODTO_UNO.
        when( iCarroService.crearCarro(any()) ).thenReturn(CarroUtilityTest.CARRODTO_UNO);

        //Llama al método crearCarro() del controlador carroController con el objeto CarroDTO
        // de CarroUtilityTest.AEROPUERTODTO_UNO.
        // Este método debería invocar internamente el método correspondiente del servicio
        // iCarroService con el mismo objeto.
        ResponseEntity<CarroDTO> response = (ResponseEntity<CarroDTO>) carroController.crearCarro(CarroUtilityTest.CARRODTO_UNO);

        //Verifica que el método crearCarro del servicio iCarroService haya sido llamado exactamente
        // una vez con el objeto CARRODTO_UNO. Se utiliza eq para comparar el objeto de manera efectiva.
        verify( iCarroService ).crearCarro( eq( CarroUtilityTest.CARRODTO_UNO ));

        assertEquals( CarroUtilityTest.CARRODTO_UNO, response.getBody() );
        assertEquals( response.getStatusCode(), HttpStatus.CREATED );
    }

    @Test
    public void listarCarrosOK() throws Exception{
        when( iCarroService.listarCarros() ).thenReturn(CarroUtilityTest.CARROSDTO);

        ResponseEntity<List<CarroDTO>> response = (ResponseEntity<List<CarroDTO>>) carroController.listarCarros();

        assertEquals( response.getBody().size(), CarroUtilityTest.CARROSDTO.size() );
        assertEquals( response.getStatusCode(), HttpStatus.OK );
    }

    @Test
    public void listarCarroCodigoOK() throws Exception{
        when( iCarroService.listarCarroCodigo( any() ) ).thenReturn(CarroUtilityTest.CARRODTO_DOS);
        ResponseEntity<CarroDTO> response = (ResponseEntity<CarroDTO>) carroController.listarPorCodigo(2);


        assertEquals( response.getBody(), CarroUtilityTest.CARRODTO_DOS );
        assertEquals( response.getStatusCode(), HttpStatus.OK );
    }

    @Test
    public void actualizarCarroOK() throws Exception{
        when( iCarroService.actualizarCarro(any()) ).thenReturn( CarroUtilityTest.CARRODTO_DOS );

        ResponseEntity<CarroDTO> response = (ResponseEntity<CarroDTO>) carroController.actualizar(CarroUtilityTest.CARRODTO_DOS);

        verify( iCarroService ).actualizarCarro(eq( CarroUtilityTest.CARRODTO_DOS ));

        assertEquals( response.getBody(), CarroUtilityTest.CARRODTO_DOS );
        assertEquals( response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void eliminarCarroOK() throws Exception{
        when( iCarroService.eliminarCarro( any() ) ).thenReturn( String.format("El Carro con id %s fue eliminado", 1));

        ResponseEntity<?> response = carroController.eliminar(1);

        verify( iCarroService ).eliminarCarro(eq( 1) );

        assertEquals( response.getBody(), String.format("El Carro con id %s fue eliminado", 1) );
        assertEquals( response.getStatusCode(), HttpStatus.OK );
    }

}
