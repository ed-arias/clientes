package com.seleccion.nuvu.clientes.domain.services;

import java.util.ArrayList;

import com.seleccion.nuvu.clientes.api.models.UsuarioModel;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DetallesUsuarioService implements UserDetailsService {

    private UsuarioService usuarioService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UsuarioModel UsuarioModel = usuarioService.listarUsuarioPorEmail(email);

        return new User(
            UsuarioModel.getEmail(),
            UsuarioModel.getPassword(),
            new ArrayList<>()
        );
    }
    
}
