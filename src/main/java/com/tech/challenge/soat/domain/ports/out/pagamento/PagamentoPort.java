package com.tech.challenge.soat.domain.ports.out.pagamento;

import com.tech.challenge.soat.domain.models.PedidoModel;

public interface PagamentoPort {
    public PedidoModel criarPagamento(PedidoModel pedido);
}