package com.tech.challenge.soat.domain.ports.out.pagamento;

import com.tech.challenge.soat.adapters.models.out.PagamentoResponse;
import com.tech.challenge.soat.domain.models.PedidoModel;

public interface PagamentoPort {
    public PagamentoResponse criarPagamento(PedidoModel pedido);
}