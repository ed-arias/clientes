package com.seleccion.nuvu.clientes.api.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.seleccion.nuvu.clientes.api.models.ClienteModel;
import com.seleccion.nuvu.clientes.api.models.TarjetaCreditoModel;
import com.seleccion.nuvu.clientes.domain.services.ClienteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteModel>> listarClientes() {
        return new ResponseEntity<>(
            clienteService.listarClientes(),
            HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> listarClientePorId(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(
            clienteService.listarClientePorId(id),
            HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<ClienteModel> registrarCliente(@Valid @RequestBody ClienteModel clienteModel) {

        clienteModel.setTarjetasCredito(new ArrayList<>());

        return new ResponseEntity<ClienteModel>(
            clienteService.registarClientes(clienteModel), 
            HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteModel> actualizarClientePorId(@PathVariable Long id, @Valid @RequestBody ClienteModel clienteModel)
            throws Exception {
        return new ResponseEntity<>(
            clienteService.actualizarCliente(id, clienteModel),
            HttpStatus.OK
        );
    }

    @GetMapping(value="/{id}/tarjetas")
    public ResponseEntity<List<TarjetaCreditoModel>> listarTarjetasCreditoPorCliente(@PathVariable long id)
            throws Exception {
        return new ResponseEntity<>(
            clienteService.listarTarjetasCreditoPorCliente(id),
            HttpStatus.OK
        );
    }
    

    @PostMapping(value = "/{id}/tarjetas")
    public ResponseEntity<TarjetaCreditoModel> registrarTarjetaCreditoACliente (
        @PathVariable long id, 
        @Valid @RequestBody TarjetaCreditoModel tarjetaCreditoModel) throws Exception {

        return new ResponseEntity<>(
            clienteService.registrarTajetaCreditoACliente(id, tarjetaCreditoModel),
            HttpStatus.CREATED
        );
    }

}
