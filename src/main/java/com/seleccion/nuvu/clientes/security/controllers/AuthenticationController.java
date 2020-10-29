package com.seleccion.nuvu.clientes.security.controllers;

import javax.validation.Valid;

import com.seleccion.nuvu.clientes.api.models.AuthenticationRequest;
import com.seleccion.nuvu.clientes.api.models.AuthenticationResponse;
import com.seleccion.nuvu.clientes.api.models.UsuarioModel;
import com.seleccion.nuvu.clientes.domain.services.DetallesUsuarioService;
import com.seleccion.nuvu.clientes.domain.services.UsuarioService;
import com.seleccion.nuvu.clientes.security.utils.JwtUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final UsuarioService usuarioService;
    private final DetallesUsuarioService detallesUsuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;

    @PostMapping(value = "/registrar")
    public ResponseEntity<UsuarioModel> registrar(@Valid @RequestBody UsuarioModel usuarioModel) {
        return new ResponseEntity<>(
            usuarioService.registrarUsuario(usuarioModel),
            HttpStatus.CREATED  
        );
    }

    @PostMapping(value = "/verificar")
    public ResponseEntity<?> verificar(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw new Exception("email o contraseña errada", e);
        }

        final UserDetails userDetails = detallesUsuarioService.loadUserByUsername(
            authenticationRequest.getEmail()
        );

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        AuthenticationResponse authResponse = new AuthenticationResponse(jwt);
        authResponse.setEmail(userDetails.getUsername());

        return ResponseEntity.ok(authResponse);
    }

}
