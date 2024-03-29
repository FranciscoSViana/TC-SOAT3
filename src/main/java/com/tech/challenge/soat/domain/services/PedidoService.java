package com.tech.challenge.soat.domain.services;

import com.tech.challenge.soat.adapters.models.in.PedidoRequest;
import com.tech.challenge.soat.adapters.models.out.PedidoResponse;
import com.tech.challenge.soat.domain.enums.StatusPedido;
import com.tech.challenge.soat.domain.models.PedidoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoService {
    PedidoResponse salvar(PedidoRequest request);

    PedidoResponse pagar(UUID id);

    PedidoResponse alterarStatus(UUID id, StatusPedido status);

    PedidoResponse buscarPedido(UUID id);

    List<PedidoResponse> buscarPedidos();

    PedidoResponse criarPagamento(UUID id);

    PedidoModel obterPorIdPagamentoMP(String idPagamento);

    PedidoModel confirmarPagamento(PedidoModel pedido);

    PedidoModel salvar(PedidoModel pedido);

    Optional<PedidoModel> obterPorUUID(String idPagamento);

    List<PedidoModel> obterPedidosComPagamentoAguardando();


}
