package com.seleccion.nuvu.clientes.api.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String jwt;
	private String email;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

}
