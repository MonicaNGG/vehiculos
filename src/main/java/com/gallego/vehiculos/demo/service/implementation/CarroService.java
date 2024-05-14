package com.gallego.vehiculos.demo.service.implementation;

import com.gallego.vehiculos.demo.dto.CarroDTO;
import com.gallego.vehiculos.demo.entities.Carro;
import com.gallego.vehiculos.demo.entities.Propietario;
import com.gallego.vehiculos.demo.exception.CarroException;
import com.gallego.vehiculos.demo.mappers.CarroMapper;
import com.gallego.vehiculos.demo.repository.CarroRepository;
import com.gallego.vehiculos.demo.repository.PropietarioRepository;
import com.gallego.vehiculos.demo.service.ICarroService;
import com.gallego.vehiculos.demo.utils.CarroValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarroService implements ICarroService {

    @Autowired
    CarroRepository carroRepository;

    @Autowired
    PropietarioRepository propietarioRepository;

    @Override
    public CarroDTO crearCarro(CarroDTO carroDTO) throws CarroException {
        validarCarro( carroDTO, true );

        Carro carro = CarroMapper.dtoToEntitie(carroDTO);
        Propietario propietario = propietarioRepository.getReferenceById( carroDTO.getPropietarioCodigo() );

        carro.setPropietario( propietario );

        return CarroMapper.entitieToDTO( carroRepository.save( carro ));
    }

    @Override
    public List<CarroDTO> listarCarros() throws CarroException {
        return CarroMapper.listEntitieToDTO( carroRepository.findAll() );
    }

    @Override
    public CarroDTO listarCarroCodigo(Integer codigo) throws CarroException {


        if(carroRepository.findById(codigo).isEmpty()){
            throw new CarroException(String.format(CarroValidate.CODIGO_NO_ENCONTRADO, codigo));
        }

        return CarroMapper.entitieToDTO( carroRepository.getReferenceById( codigo ));
    }

    @Override
    public CarroDTO actualizarCarro(CarroDTO carroDTO) throws CarroException {

        validarCarro( carroDTO, false );

        Carro carro = CarroMapper.dtoToEntitie(carroDTO);
        Propietario propietario = propietarioRepository.getReferenceById( carroDTO.getPropietarioCodigo() );

        carro.setPropietario( propietario );

        return CarroMapper.entitieToDTO( carroRepository.save( carro ));
    }

    @Override
    public String eliminarCarro(Integer Id) throws CarroException {

        if(carroRepository.findById(Id).isEmpty()){
            throw new CarroException(String.format(CarroValidate.CODIGO_NO_ENCONTRADO, Id));
        }

        CarroDTO carroDTO = listarCarroCodigo( Id );

        carroRepository.delete(CarroMapper.dtoToEntitie(carroDTO));

        return String.format(CarroValidate.CARRO_ELIMINADO, Id );
    }

    public void validarCarro( CarroDTO carroDTO, boolean esGuardar) throws CarroException{

        if( carroDTO == null ){
            throw new CarroException(CarroValidate.CARRO_NO_NULL);
        }
        if( carroDTO.getModelo() == null ){
            throw new CarroException(CarroValidate.MODELO_NO_NULL);
        }
        if( carroDTO.getPlaca() == null || carroDTO.getPlaca().isEmpty()){
            throw new CarroException(CarroValidate.PLACA_NO_NULL);
        }
        if( carroDTO.getPropietarioCodigo() == null ){
            throw new CarroException(CarroValidate.PROPIETARIO_NO_NULL);
        }

        if(propietarioRepository.findById(carroDTO.getPropietarioCodigo()).isEmpty()){
            throw new CarroException( String.format(CarroValidate.PROPIETARIO_NO_ENCONTRADO, carroDTO.getPropietarioCodigo()));
        }

        if( esGuardar ){
            if(carroRepository.findByPlaca(carroDTO.getPlaca()).isPresent()){
                throw  new CarroException( String.format(CarroValidate.PLACA_EN_USO, carroDTO.getPlaca() ) );
            }
        }

        if( !esGuardar ) {
            if (carroRepository.findById(carroDTO.getCodigo()).isEmpty()) {
                throw new CarroException(String.format(CarroValidate.CODIGO_NO_ENCONTRADO, carroDTO.getCodigo()));
            }
        }
    }
}
