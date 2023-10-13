package com.tech.challenge.soat.core.applications.feign.service;

import com.tech.challenge.soat.core.applications.feign.client.request.PagamentoRequest;
import com.tech.challenge.soat.core.applications.feign.client.response.PagamentoResponse;

@FunctionalInterface
public interface MercadoPagoService {

    PagamentoResponse criarPagamento(PagamentoRequest pagamento);

}
