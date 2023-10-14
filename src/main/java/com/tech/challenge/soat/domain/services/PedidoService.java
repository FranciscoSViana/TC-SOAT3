package com.tech.challenge.soat.domain.services;

import com.tech.challenge.soat.adapters.driver.v1.model.request.PedidoRequest;
import com.tech.challenge.soat.domain.models.PedidoModel;
import com.tech.challenge.soat.core.enumerator.StatusPedido;

import java.util.List;
import java.util.UUID;

public interface PedidoService {
    PedidoModel salvar(PedidoRequest savePedidoRequest);

    PedidoModel pagar(UUID id);

    PedidoModel alterarStatus(UUID id, StatusPedido status);

    PedidoModel buscarPedido(UUID id);

    List<PedidoModel> buscarPedidos();
}
