package com.seleccion.nuvu.clientes.domain.repositories;

import java.util.Optional;

import com.seleccion.nuvu.clientes.domain.entities.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByEmail(String email);

}
