package com.tech.challenge.soat.core.applications.feign.service;

import com.tech.challenge.soat.adapters.driver.v1.model.request.PagamentoMPRequest;
import com.tech.challenge.soat.core.applications.feign.client.MercadoPagoClient;
import com.tech.challenge.soat.core.applications.feign.client.request.Pagador;
import com.tech.challenge.soat.core.applications.feign.client.request.PagamentoRequest;
import com.tech.challenge.soat.core.applications.feign.client.response.PagamentoResponse;
import com.tech.challenge.soat.shared.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MercadoPagoServiceImpl implements MercadoPagoService{

    private static final String PIX = "pix";
    private static final String QR_CODE = "qr_code";
    private static final String QR_CODE_BASE_64 = "qr_code_base64";

    private final MercadoPagoClient mercadoPagoClient;

    private final JsonUtil jsonUtil;

    @Value("${mp.token}")
    private String authorization;

    @Override
    public PagamentoResponse criarPagamento(PagamentoMPRequest pagamento) {

        String response = mercadoPagoClient.criarPagamento(authorization, PagamentoRequest.builder()
                        .valor(pagamento.getValor())
                        .payer(Pagador.builder().email(pagamento.getEmail()).build())
                        .metodoDePagamento(PIX)
                .build());

        String qrCopiaCola = jsonUtil.obterValorChaveJson(response, QR_CODE);

        String qrImage = jsonUtil.obterValorChaveJson(response, QR_CODE_BASE_64);

        return PagamentoResponse.builder()
                    .codigoPix(qrCopiaCola)
                    .qrCode(qrImage)
                    .build();

    }


}
