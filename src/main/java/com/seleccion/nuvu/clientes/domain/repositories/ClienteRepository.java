package com.seleccion.nuvu.clientes.domain.repositories;

import java.util.Optional;

import com.seleccion.nuvu.clientes.domain.entities.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public Optional<Cliente> findByEmail(String email);
    
}
