package com.gallego.vehiculos.demo.service.implementation;

import com.gallego.vehiculos.demo.dto.PropietarioDTO;
import com.gallego.vehiculos.demo.entities.Propietario;
import com.gallego.vehiculos.demo.exception.PropietarioException;
import com.gallego.vehiculos.demo.mappers.PropietarioMapper;
import com.gallego.vehiculos.demo.repository.PropietarioRepository;
import com.gallego.vehiculos.demo.service.IPropietarioService;
import com.gallego.vehiculos.demo.utils.PropietarioValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropietarioService implements IPropietarioService {

    @Autowired
    PropietarioRepository propietarioRepository;

    @Override
    public PropietarioDTO crearPropietario(PropietarioDTO propietarioDTO) throws PropietarioException {

        validarPropietario( propietarioDTO, true );

        Propietario propietario = PropietarioMapper.dtoToEntitie( propietarioDTO );
        return PropietarioMapper.entitieToDTO( propietarioRepository.save( propietario ));
    }

    @Override
    public List<PropietarioDTO> listarPropietarios() throws PropietarioException {
        return PropietarioMapper.listEntitieToDTO( propietarioRepository.findAll() );
    }

    @Override
    public PropietarioDTO obtenerPropietarioCodigo(Integer codigo) throws PropietarioException {
        if( propietarioRepository.findById(codigo).isEmpty()){
            throw new PropietarioException(String.format(PropietarioValidate.PROPIETARIO_NO_EXISTE_IDENTI, codigo ));
        }

        return PropietarioMapper.entitieToDTO( propietarioRepository.getReferenceById( codigo ));
    }

    @Override
    public PropietarioDTO actualizarPropietario(PropietarioDTO propietarioDTO) throws PropietarioException {
        validarPropietario( propietarioDTO, false );
        Propietario propietario = PropietarioMapper.dtoToEntitie( propietarioDTO );
        return PropietarioMapper.entitieToDTO( propietarioRepository.save( propietario ));
    }

    @Override
    public String eliminarPropietario(Integer codigo) throws PropietarioException {

        if( propietarioRepository.findById(codigo).isEmpty()){
            throw new PropietarioException(String.format(PropietarioValidate.PROPIETARIO_NO_EXISTE_IDENTI, codigo ));
        }

        PropietarioDTO propietarioDTO = obtenerPropietarioCodigo( codigo );
        propietarioRepository.delete( PropietarioMapper.dtoToEntitie( propietarioDTO ));
        return String.format( PropietarioValidate.PROPIETARIO_ELIMINADO, codigo );
    }


    public void validarPropietario( PropietarioDTO propietarioDTO, boolean esGuardar ) throws PropietarioException{
        if (propietarioDTO == null) {
            throw new PropietarioException(PropietarioValidate.PROPIETARIO_NO_NULL);
        }
        if (propietarioDTO.getNombre() == null || propietarioDTO.getNombre().isEmpty()) {
            throw new PropietarioException(PropietarioValidate.NOMBRE_NO_NULL);
        }

        if (propietarioDTO.getIdentificacion() == null) {
            throw new PropietarioException(PropietarioValidate.IDENTIFICACION_NO_NULL);
        }

        if( esGuardar ){
            if( propietarioRepository.findByNombre( propietarioDTO.getNombre() ) != null ){
                throw new PropietarioException(String.format(PropietarioValidate.PROPIETARIO_EXISTE_NOMBRE, propietarioDTO.getNombre() ));
            }
            if( propietarioRepository.findByIdentificacion(propietarioDTO.getIdentificacion()) != null ){
                throw new PropietarioException(String.format(PropietarioValidate.PROPIETARIO_EXISTE_NOMBRE, propietarioDTO.getNombre() ));
            }
        }

        if( !esGuardar ){
            if( propietarioRepository.findById(propietarioDTO.getCodigo()).isEmpty() ){
                throw new PropietarioException(String.format(PropietarioValidate.CODIGO_NO_ENCONTRADO, propietarioDTO.getCodigo()));
            }
        }
    }
}
