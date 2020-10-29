package com.seleccion.nuvu.clientes.api.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.seleccion.nuvu.clientes.domain.entities.enums.TipoDocumento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteModel implements Serializable {

    private static final long serialVersionUID = -4091913263388863121L;

    private Long id;

    @NotNull(message = "el numero de documento no puede estar vacio")
    private Long documento;

    @NotNull(message = "el tipo de documento no puede estar vacio")
    private TipoDocumento tipoDocumento;

    @NotBlank(message = "el primer nombre no puede estar vacio")
    private String primerNombre;

    private String segundoNombre;

    @NotBlank(message = "el primer apellido no puede estar vacio")
    private String primerApellido;

    private String segundoApellido;
    private LocalDate fechaNacimiento;
    private String direccion;

    @Email
    @NotBlank(message = "el primer apellido no puede estar vacio")
    private String email;
    
    private List<TarjetaCreditoModel> tarjetasCredito;

}
