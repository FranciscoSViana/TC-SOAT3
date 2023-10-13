package com.tech.challenge.soat.core.applications.feign.service;

import com.tech.challenge.soat.adapters.driver.v1.model.request.PagamentoMPRequest;
import com.tech.challenge.soat.core.applications.feign.client.request.PagamentoRequest;
import com.tech.challenge.soat.core.applications.feign.client.response.PagamentoResponse;

@FunctionalInterface
public interface MercadoPagoService {

    PagamentoResponse criarPagamento(PagamentoMPRequest pagamento);

}
