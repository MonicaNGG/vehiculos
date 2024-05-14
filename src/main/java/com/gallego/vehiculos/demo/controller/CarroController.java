package com.gallego.vehiculos.demo.controller;


import com.gallego.vehiculos.demo.dto.CarroDTO;
import com.gallego.vehiculos.demo.dto.ErrorDTO;
import com.gallego.vehiculos.demo.exception.CarroException;
import com.gallego.vehiculos.demo.service.ICarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carros")
public class CarroController {

    @Autowired
    ICarroService iCarroService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearCarro(@Validated @RequestBody CarroDTO carroDTO ){
        try{
            return new ResponseEntity( iCarroService.crearCarro(carroDTO), HttpStatus.CREATED );
        } catch( CarroException ex ){
            return new ResponseEntity(ErrorDTO.builder().mensaje( ex.getMessage() ).build(), HttpStatus.BAD_REQUEST );
        }
    }


    @GetMapping("/listar")
    public ResponseEntity<?> listarCarros(){
        try{
            return new ResponseEntity( iCarroService.listarCarros(), HttpStatus.OK );
        } catch ( CarroException ex ){
            return new ResponseEntity( ErrorDTO.builder().mensaje( ex.getMessage() ).build(), HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping("/listarPorCodigo/{codigo}")
    public ResponseEntity<?> listarPorCodigo( @PathVariable Integer codigo ){
        try{
            return new ResponseEntity( iCarroService.listarCarroCodigo(codigo), HttpStatus.OK );
        } catch ( CarroException ex ){
            return new ResponseEntity( ErrorDTO.builder().mensaje( ex.getMessage() ).build(), HttpStatus.BAD_REQUEST );
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar( @RequestBody CarroDTO carroDTO ){
        try{
            return new ResponseEntity( iCarroService.actualizarCarro(carroDTO), HttpStatus.OK );
        } catch ( CarroException ex ){
            return new ResponseEntity( ErrorDTO.builder().mensaje( ex.getMessage() ).build(), HttpStatus.BAD_REQUEST );
        }
    }


    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<?> eliminar( @PathVariable Integer codigo ){
        try{
            return new ResponseEntity( iCarroService.eliminarCarro(codigo), HttpStatus.OK );
        } catch ( CarroException ex ){
            return new ResponseEntity( ErrorDTO.builder().mensaje( ex.getMessage() ).build(), HttpStatus.BAD_REQUEST );
        }
    }
}
