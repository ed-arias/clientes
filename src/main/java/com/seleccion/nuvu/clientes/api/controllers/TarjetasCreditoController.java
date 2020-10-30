package com.seleccion.nuvu.clientes.api.controllers;

import java.util.List;

import javax.validation.Valid;

import com.seleccion.nuvu.clientes.api.models.TarjetaCreditoModel;
import com.seleccion.nuvu.clientes.domain.services.TarjetaCreditoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/tarjetas")
public class TarjetasCreditoController {

    private final TarjetaCreditoService tarjetaCreditoService;

    @GetMapping
    public ResponseEntity<List<TarjetaCreditoModel>> listarTarjetas() {
        return new ResponseEntity<>(
            tarjetaCreditoService.listarTarjetasCredito(),
            HttpStatus.OK
        );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TarjetaCreditoModel> listarTarjetaPorId(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(
            tarjetaCreditoService.listarTarjetaCreditoPorId(id),
            HttpStatus.OK
        );
    }

}
