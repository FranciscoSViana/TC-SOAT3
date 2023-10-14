package com.tech.challenge.soat.core.applications.feign.service;

import com.tech.challenge.soat.adapters.driver.v1.model.request.PagamentoMPRequest;
import com.tech.challenge.soat.core.applications.feign.client.MercadoPagoClient;
import com.tech.challenge.soat.core.applications.feign.client.request.Pagador;
import com.tech.challenge.soat.core.applications.feign.client.request.PagamentoRequest;
import com.tech.challenge.soat.core.applications.feign.client.response.PagamentoResponse;
import com.tech.challenge.soat.core.exception.NotFoundException;
import com.tech.challenge.soat.shared.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MercadoPagoServiceImpl implements MercadoPagoService{

    private static final String PIX = "pix";
    private static final String QR_CODE = "qr_code";
    private static final String QR_CODE_BASE_64 = "qr_code_base64";
    public static final String OCORREU_UM_ERRO_NA_COMUNICACAO_COM_MERCADO_PAGO = "Ocorreu um erro na comunicação com Mercado Pago, {}";

    private final MercadoPagoClient mercadoPagoClient;

    private final JsonUtil jsonUtil;

    @Value("${mp.token}")
    private String authorization;

    @Override
    public PagamentoResponse criarPagamento(PagamentoMPRequest pagamento) {

        try {
            String response = mercadoPagoClient.criarPagamento(authorization, PagamentoRequest.builder()
                    .valor(pagamento.getValor())
                    .payer(Pagador.builder().email(pagamento.getEmail()).build())
                    .metodoDePagamento(PIX)
                    .build());


            return PagamentoResponse.builder()
                    .codigoPix(jsonUtil.obterValorChaveJson(response, QR_CODE))
                    .qrCode(jsonUtil.obterValorChaveJson(response, QR_CODE_BASE_64))
                    .build();

        }catch(Exception err) {
            throw new NotFoundException(format(OCORREU_UM_ERRO_NA_COMUNICACAO_COM_MERCADO_PAGO,err.getMessage()));
        }

    }


}
