package com.seleccion.nuvu.clientes.api.models;

import java.time.LocalDate;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.seleccion.nuvu.clientes.domain.entities.enums.EntidadEmisora;
import com.seleccion.nuvu.clientes.domain.entities.enums.Franquicia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarjetaCreditoModel {

    private Long id;

    @NotBlank(message = "el numero de la tarjeta no puede estar vacio")
    @Size(min = 16, max = 16, message = "el número de la tarjeta debe ser de 16 digitos")
    private String numero;

    @Digits(fraction = 0, integer = 3, message = "el número CCV debe ser de 3 digitos")
    private Integer ccv;

    @NotNull(message = "la franquicia no puede estar vacia")
    private Franquicia franquicia;
    
    @NotNull(message = "la entidad emisora no puede estar vacia")
    private EntidadEmisora entidadEmisora;
    
    private LocalDate fechaExpriracion;

}