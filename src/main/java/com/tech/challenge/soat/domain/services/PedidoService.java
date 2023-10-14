package com.tech.challenge.soat.domain.services;

import com.tech.challenge.soat.domain.enums.StatusPedido;
import com.tech.challenge.soat.domain.models.PedidoModel;

import java.util.List;
import java.util.UUID;

public interface PedidoService {
    PedidoModel salvar(PedidoModel pedido);

    PedidoModel pagar(UUID id);

    PedidoModel alterarStatus(UUID id, StatusPedido status);

    PedidoModel buscarPedido(UUID id);

    List<PedidoModel> buscarPedidos();

    PedidoModel criarPagamento(UUID id);
}
