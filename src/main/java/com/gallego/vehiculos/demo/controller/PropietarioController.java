package com.gallego.vehiculos.demo.controller;


import com.gallego.vehiculos.demo.dto.ErrorDTO;
import com.gallego.vehiculos.demo.dto.PropietarioDTO;
import com.gallego.vehiculos.demo.exception.PropietarioException;
import com.gallego.vehiculos.demo.service.IPropietarioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/propietarios")
public class PropietarioController {

    @Autowired
    IPropietarioService ipropietarioService;

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@Validated @RequestBody PropietarioDTO propietarioDTO ){
        try{
            return new ResponseEntity<>( ipropietarioService.crearPropietario(propietarioDTO), HttpStatus.CREATED );
        } catch( PropietarioException ex ){
            return new ResponseEntity<>(ErrorDTO.builder().mensaje( ex.getMessage()).build() , HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        try{
            return new ResponseEntity<>( ipropietarioService.listarPropietarios(), HttpStatus.OK );
        } catch (PropietarioException ex ){
            return new ResponseEntity( ErrorDTO.builder().mensaje( ex.getMessage() ).build(), HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping("/listarPorCodigo/{codigo}")
    public ResponseEntity<?> listarPorCodigo( @PathVariable Integer codigo ){
        try{
            return new ResponseEntity( ipropietarioService.obtenerPropietarioCodigo(codigo), HttpStatus.OK );
        } catch ( PropietarioException ex ){
            return new ResponseEntity( ErrorDTO.builder().mensaje( ex.getMessage() ).build(), HttpStatus.BAD_REQUEST );
        }
    }


    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar( @RequestBody PropietarioDTO propietarioDTO ){
        try{
            return new ResponseEntity( ipropietarioService.actualizarPropietario(propietarioDTO), HttpStatus.OK );
        } catch ( PropietarioException ex ){
            return new ResponseEntity( ErrorDTO.builder().mensaje( ex.getMessage() ).build(), HttpStatus.BAD_REQUEST );
        }
    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<?> eliminar( @PathVariable Integer codigo ){
        try{
            return new ResponseEntity( ipropietarioService.eliminarPropietario(codigo), HttpStatus.OK );
        } catch ( PropietarioException ex ){
            return new ResponseEntity( ErrorDTO.builder().mensaje( ex.getMessage() ).build(), HttpStatus.BAD_REQUEST );
        }
    }
}
