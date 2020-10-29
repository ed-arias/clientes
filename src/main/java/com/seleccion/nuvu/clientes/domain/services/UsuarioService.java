package com.seleccion.nuvu.clientes.domain.services;

import java.time.LocalDateTime;

import com.seleccion.nuvu.clientes.api.models.UsuarioModel;
import com.seleccion.nuvu.clientes.domain.entities.Usuario;
import com.seleccion.nuvu.clientes.domain.repositories.UsuarioRepository;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public UsuarioModel listarUsuarioPorEmail(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("email no encontrado: " + email));

        return toUsuarioModel(usuario);
    }

    @Transactional
    public UsuarioModel registrarUsuario(UsuarioModel usuarioDTO) {

        usuarioDTO.setFechaCreacion(LocalDateTime.now());

        usuarioRepository.save(toUsuario(usuarioDTO));

        return usuarioDTO;
    }

    private Usuario toUsuario(UsuarioModel usuarioDTO) {
        return Usuario.builder()
            .email(usuarioDTO.getEmail())
            .fechaCreacion(LocalDateTime.now())
            .password(usuarioDTO.getPassword())
            .build();
    }

    private UsuarioModel toUsuarioModel(Usuario usuario) {
        return UsuarioModel.builder()
            .email(usuario.getEmail())
            .password(usuario.getPassword())
            .fechaCreacion(usuario.getFechaCreacion())
            .build();
    }
}
