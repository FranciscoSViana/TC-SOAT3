package com.tech.challenge.soat.domain.ports.out.pagamento;

import com.tech.challenge.soat.domain.models.PedidoModel;

public interface PagamentoPort {
    PedidoModel criarPagamento(PedidoModel pedido);
    String confirmarPagamento(String idPagamento);
}