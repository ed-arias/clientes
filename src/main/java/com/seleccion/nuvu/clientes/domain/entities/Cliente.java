package com.seleccion.nuvu.clientes.domain.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.seleccion.nuvu.clientes.domain.entities.enums.TipoDocumento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Table(
    name = "tb_cliente", 
    uniqueConstraints = { 
        @UniqueConstraint(name = "unique_email", columnNames = { "email" }),
        @UniqueConstraint(name = "unique_client", columnNames = { "documento", "tipoDocumento" }) })
public class Cliente implements Serializable {

    private static final long serialVersionUID = 4568922971492533756L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long documento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @Column(nullable = false)
    private String primerNombre;

    private String segundoNombre;

    @Column(nullable = false)
    private String primerApellido;

    private String segundoApellido;
    private LocalDate fechaNacimiento;
    private String direccion;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @OneToMany
    @JoinColumn(name = "id_cliente")
    private List<TarjetaCredito> tarjetasCredito;

}
