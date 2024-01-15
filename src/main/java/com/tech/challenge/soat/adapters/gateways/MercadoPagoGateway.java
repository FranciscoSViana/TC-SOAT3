package com.tech.challenge.soat.adapters.gateways;

import com.tech.challenge.soat.adapters.models.out.PagadorMP;
import com.tech.challenge.soat.adapters.models.out.PagamentoMPRequest;
import com.tech.challenge.soat.domain.models.PedidoModel;
import com.tech.challenge.soat.domain.ports.out.pagamento.PagamentoPort;
import com.tech.challenge.soat.domain.utils.JsonUtil;
import com.tech.challenge.soat.infra.client.MercadoPagoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MercadoPagoGateway implements PagamentoPort {

    private static final String PIX = "pix";
    private static final String QR_CODE = "qr_code";
    private static final String QR_CODE_BASE_64 = "qr_code_base64";
    public static final String ID = "id";

    private final MercadoPagoClient mercadoPagoClient;

    private final JsonUtil jsonUtil;


    @Value("${mp.token}")
    private String authorization;


    @Value("${mp.url.rota}")
    private String urlWebhook;

    @Override
    public PedidoModel criarPagamento(PedidoModel pedido) {

        String response = mercadoPagoClient.criarPagamento(authorization, PagamentoMPRequest.builder()
                .valor(pedido.getPreco())
                .payer(PagadorMP.builder().email(pedido.getCliente().getEmail()).build())
                .metodoDePagamento(PIX)
                .enderecoNotificacao(urlWebhook)
                .build());

        String qrCopiaCola = jsonUtil.obterValorChaveJson(response, QR_CODE);

        String qrImage = jsonUtil.obterValorChaveJson(response, QR_CODE_BASE_64);

        String idPagamentoMP = jsonUtil.obterValorChaveJson(response, ID);

        pedido.setCodigoPix(qrCopiaCola);

        pedido.setQrCode(qrImage.getBytes());

        pedido.setIdPagamentoMP(idPagamentoMP);

        return  pedido;
    }

    @Override
    public String confirmarPagamento(String idPagamento) {
        return mercadoPagoClient.confirmarPagamento(authorization,idPagamento);
    }
}
