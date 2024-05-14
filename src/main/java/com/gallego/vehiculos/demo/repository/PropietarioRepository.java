package com.gallego.vehiculos.demo.repository;

import com.gallego.vehiculos.demo.entities.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// <Propietario = Entidad conectada a tabla en db, Integer = Tipo dato llave primaria tabla >
//JPA nos da metodos predeterminados para evitar usar consultas y ademas conectarnos
//A la DB que esta configurada en el properties

@Repository
public interface PropietarioRepository extends JpaRepository<Propietario, Integer> {
    Propietario findByNombre( String nombre );
    Propietario findByIdentificacion(Long identificacion);
}

// SELECT * FROM propietarios WHERE nombre = string nombre;