package com.seleccion.nuvu.clientes.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import com.seleccion.nuvu.clientes.api.models.TarjetaCreditoModel;
import com.seleccion.nuvu.clientes.domain.entities.TarjetaCredito;
import com.seleccion.nuvu.clientes.domain.repositories.TarjetaCreditoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TarjetaCreditoService {

    private final TarjetaCreditoRepository tarjetaCreditoRepository;

    @Transactional
    public TarjetaCreditoModel registrarTarjeta(TarjetaCreditoModel tarjetaCreditoModel) {
        TarjetaCredito tarjetaCredito = tarjetaCreditoRepository.save(toTarjetaCredito(tarjetaCreditoModel));

        tarjetaCreditoModel.setId(tarjetaCredito.getId());

        return tarjetaCreditoModel;
    }

    @Transactional(readOnly = true)
    public List<TarjetaCreditoModel> listarTarjetasCredito() {
        return tarjetaCreditoRepository.findAll()
            .stream()
            .map(this::toTarjetaCreditoModel)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TarjetaCreditoModel listarTarjetaCreditoPorId(Long id) throws Exception {

        TarjetaCredito tarjetaCredito = tarjetaCreditoRepository.findById(id)
                .orElseThrow(() -> new Exception("tarjeta no encontrada con id = " + id));

        return toTarjetaCreditoModel(tarjetaCredito);

    }

    public TarjetaCreditoModel toTarjetaCreditoModel(TarjetaCredito tarjetaCredito) {
        return TarjetaCreditoModel.builder()
            .id(tarjetaCredito.getId())
            .ccv(tarjetaCredito.getCcv())
            .entidadEmisora(tarjetaCredito.getEntidadEmisora())
            .fechaExpriracion(tarjetaCredito.getFechaExpriracion())
            .franquicia(tarjetaCredito.getFranquicia())
            .numero(tarjetaCredito.getNumero())
            .build();
    }

    public TarjetaCredito toTarjetaCredito(TarjetaCreditoModel tarjetaCreditoModel) {
        return TarjetaCredito.builder()
            .id(tarjetaCreditoModel.getId())
            .ccv(tarjetaCreditoModel.getCcv())
            .entidadEmisora(tarjetaCreditoModel.getEntidadEmisora())
            .fechaExpriracion(tarjetaCreditoModel.getFechaExpriracion())
            .franquicia(tarjetaCreditoModel.getFranquicia())
            .numero(tarjetaCreditoModel.getNumero())
            .build();
    }

}
