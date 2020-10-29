package com.seleccion.nuvu.clientes.domain.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Entity
@Table(
    name = "tb_tarjeta_credito", 
    uniqueConstraints = {
        @UniqueConstraint(columnNames = { "numero", "entidadEmisora" }, name = "unique_numero_entidadEmisora") })
public class TarjetaCredito implements Serializable {

    private static final long serialVersionUID = -3316984377516916990L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numero;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Franquicia franquicia;

    private Integer ccv;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EntidadEmisora entidadEmisora;

    private LocalDate fechaExpriracion;

}
