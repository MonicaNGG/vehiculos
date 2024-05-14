package com.gallego.vehiculos.demo.repository;

import com.gallego.vehiculos.demo.entities.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer> {
    Optional<Carro> findByPlaca(String integer );
}
