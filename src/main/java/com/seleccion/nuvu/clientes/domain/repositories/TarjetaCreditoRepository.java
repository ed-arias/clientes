package com.seleccion.nuvu.clientes.domain.repositories;

import com.seleccion.nuvu.clientes.domain.entities.TarjetaCredito;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaCreditoRepository extends JpaRepository<TarjetaCredito, Long> {
    
}
