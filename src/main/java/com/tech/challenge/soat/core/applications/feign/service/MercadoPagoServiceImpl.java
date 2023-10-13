package com.tech.challenge.soat.core.applications.feign.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.challenge.soat.core.applications.feign.client.MercadoPagoClient;
import com.tech.challenge.soat.core.applications.feign.client.request.Pagador;
import com.tech.challenge.soat.core.applications.feign.client.request.PagamentoRequest;
import com.tech.challenge.soat.core.applications.feign.client.response.PagamentoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MercadoPagoServiceImpl implements MercadoPagoService{

    private final MercadoPagoClient mercadoPagoClient;

    @Value("${mp.token}")
    private String authorization;


    @Override
    public PagamentoResponse criarPagamento(PagamentoRequest pagamento) {

        String accept = "application/json";

        String contentType = "application/json";

        String response = mercadoPagoClient.criarPagamento(authorization, accept, contentType, montaPagamento());

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(response, PagamentoResponse.class);

        } catch (JsonProcessingException e) {

            throw new RuntimeException(e);
        }

    }

    private PagamentoRequest montaPagamento() {
        return PagamentoRequest.builder()
                .valor(BigDecimal.valueOf(213.54))
                .metodoDePagamento("pix")
                .payer(Pagador.builder()
                        .email("pix@fiap.com")
                        .build())
                .build();
    }
}
