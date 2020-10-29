package com.seleccion.nuvu.clientes.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import com.seleccion.nuvu.clientes.api.models.ClienteModel;
import com.seleccion.nuvu.clientes.api.models.TarjetaCreditoModel;
import com.seleccion.nuvu.clientes.domain.entities.Cliente;
import com.seleccion.nuvu.clientes.domain.repositories.ClienteRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final TarjetaCreditoService tarjetaCreditoService;
    
    @Transactional
    public ClienteModel registarClientes(ClienteModel clienteModel) {
        
        Cliente cliente = clienteRepository.save(
            toCliente(clienteModel)
        );

        clienteModel.setId(cliente.getId());

        return clienteModel;
    }

    @Transactional(readOnly = true)
    public List<ClienteModel> listarClientes() {
        return clienteRepository.findAll()
            .stream()
            .map(
                cliente -> toClienteModel(cliente))
                .collect(Collectors.toList()
            );

    }

    @Transactional(readOnly = true)
    public ClienteModel listarClientePorId(Long id) throws Exception {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new Exception("Cliente no encontrado con id = " + id));

        return toClienteModel(cliente);
    }

    @Transactional
    public ClienteModel actualizarCliente(Long id, ClienteModel clienteModel) throws Exception {
        listarClientePorId(id);
        clienteModel.setId(id);
        registarClientes(clienteModel);
        return clienteModel;
    }

    public List<TarjetaCreditoModel> listarTarjetasCreditoPorCliente(Long id) throws Exception {
        
        //Obtener cliente por id
        ClienteModel clienteModel = listarClientePorId(id);

        return clienteModel.getTarjetasCredito();
    }

    @Transactional
    public TarjetaCreditoModel registrarTajetaCreditoACliente(Long clienteId, TarjetaCreditoModel tarjetaCreditoModel)
            throws Exception {

        //Obtener cliente por id
        ClienteModel clienteModel = listarClientePorId(clienteId);

        //Obtener tarjetas de credito del cliente
        List<TarjetaCreditoModel> tarjetasCreditoDTO = clienteModel.getTarjetasCredito();

        //Registrar tarjeta de credito en el sistema
        TarjetaCreditoModel tarjetaCreditoRegistradaModel = tarjetaCreditoService.registrarTarjeta(tarjetaCreditoModel);

        //Vincular tarjeta y cliente
        tarjetasCreditoDTO.add(tarjetaCreditoRegistradaModel);

        //Actualizar cliente
        actualizarCliente(clienteId, clienteModel);

        return tarjetaCreditoRegistradaModel;
    }

    private ClienteModel toClienteModel(Cliente cliente) {
        return ClienteModel.builder()
            .direccion(cliente.getDireccion())
            .documento(cliente.getDocumento())
            .email(cliente.getEmail())
            .fechaNacimiento(cliente.getFechaNacimiento())
            .id(cliente.getId())
            .primerApellido(cliente.getPrimerApellido())
            .primerNombre(cliente.getPrimerNombre())
            .segundoApellido(cliente.getSegundoApellido())
            .segundoNombre(cliente.getSegundoNombre())
            .tarjetasCredito(
                cliente.getTarjetasCredito()
                .stream()
                .map(tarjetaCreditoService::toTarjetaCreditoModel)
                .collect(Collectors.toList())
            )
            .tipoDocumento(cliente.getTipoDocumento())
            .build();
    }

    private Cliente toCliente(ClienteModel clienteModel){
        return Cliente.builder()
            .direccion(clienteModel.getDireccion())
            .documento(clienteModel.getDocumento())
            .email(clienteModel.getEmail())
            .fechaNacimiento(clienteModel.getFechaNacimiento())
            .id(clienteModel.getId())
            .primerApellido(clienteModel.getPrimerApellido())
            .primerNombre(clienteModel.getPrimerNombre())
            .segundoApellido(clienteModel.getSegundoApellido())
            .segundoNombre(clienteModel.getSegundoNombre())
            .tarjetasCredito(
                clienteModel.getTarjetasCredito()
                .stream()
                .map(tarjetaCreditoService::toTarjetaCredito)
                .collect(Collectors.toList())
            )
            .tipoDocumento(clienteModel.getTipoDocumento())
            .build();
    }
}
