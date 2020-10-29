package com.seleccion.nuvu.clientes.api.models;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioModel {
    
    @Email
    @NotBlank(message = "el email no puede estar vacio")
    private String email;

    @NotBlank(message = "el password no puede estar vacio")
    private String password;

    private LocalDateTime fechaCreacion;
}
