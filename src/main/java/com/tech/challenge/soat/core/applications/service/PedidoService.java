package com.tech.challenge.soat.core.applications.service;

import com.tech.challenge.soat.adapters.driver.v1.model.request.PedidoRequest;
import com.tech.challenge.soat.core.domain.Pedido;
import com.tech.challenge.soat.core.enumerator.StatusPedido;

import java.util.List;
import java.util.UUID;

public interface PedidoService {
    Pedido salvar(PedidoRequest savePedidoRequest);

    Pedido pagar(UUID id);

    Pedido alterarStatus(UUID id, StatusPedido status);

    Pedido buscarPedido(UUID id);

    List<Pedido> buscarPedidos();
}
