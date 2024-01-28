package com.tech.challenge.soat.adapters.schedulers;

import com.tech.challenge.soat.adapters.gateways.MercadoPagoGateway;
import com.tech.challenge.soat.domain.models.PedidoModel;
import com.tech.challenge.soat.domain.services.PedidoService;
import com.tech.challenge.soat.domain.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class IntegracaoMPScheduler {

    private static final String QR_CODE = "qr_code";

    private final PedidoService pedidoService;

    private final MercadoPagoGateway mercadoPagoGateway;

    private final JsonUtil jsonUtil;


    @Scheduled(fixedRate = 12000)
    public void executarRotinaMP() {

        List<PedidoModel> pedidos = pedidoService.obterPedidosComPagamentoAguardando();

        for (PedidoModel pedido : pedidos) {

            String payload = mercadoPagoGateway.confirmarPagamento(Long.valueOf(pedido.getIdPagamentoMP()));

            String situacao = removeAspas(jsonUtil.obterValorChaveJson(payload, "status"));

            if (situacao.equalsIgnoreCase("pending")) {
                pedidoService.confirmarPagamento(pedido);
            }

        }

    }

    private String removeAspas(String palavra) {
        return palavra.replace("\"", "");
    }


}
