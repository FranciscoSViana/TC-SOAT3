package com.tech.challenge.soat.application.service;

import com.tech.challenge.soat.adapters.gateways.MercadoPagoGateway;
import com.tech.challenge.soat.domain.models.PedidoModel;
import com.tech.challenge.soat.domain.services.PedidoService;
import com.tech.challenge.soat.domain.services.WebhookMPService;
import com.tech.challenge.soat.domain.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class WebhookMPServiceImpl implements WebhookMPService {


    private final JsonUtil jsonUtil;

    private final PedidoService pedidoService;

    private final MercadoPagoGateway mercadoPagoGateway;

    private static final String QR_CODE = "qr_code";
    private static final String QR_CODE_BASE_64 = "qr_code_base64";
    private static final String ID = "id";
    private static final String DATA = "data";
    private static final String DESCRIPTION = "description";

    @Override
    public void integracaoMP(String payload) {

        String uuid = null;

        String qrCopiaCola = null;

        String qrImage = null;

        String data = jsonUtil.obterValorChaveJson(payload, DATA);

        String idPagamentoMP = jsonUtil.obterValorChaveJson(data, ID);

        String idPagamento = removeAspas(idPagamentoMP);

        Long id = (idPagamento != null) ? Long.parseLong(idPagamento) : null;

        if (id != null) {

            String pagamentoMP = mercadoPagoGateway.confirmarPagamento(id);

            qrCopiaCola = jsonUtil.obterValorChaveJson(pagamentoMP, QR_CODE);

            qrImage = jsonUtil.obterValorChaveJson(pagamentoMP, QR_CODE_BASE_64);

            String uuidPedido = jsonUtil.obterValorChaveJson(pagamentoMP, DESCRIPTION);

            uuid = removeAspas(uuidPedido);
        }


        if (uuid != null) {

            Optional<PedidoModel> pedido = pedidoService.obterPorUUID(uuid);

            if (pedido.isPresent()) {

                PedidoModel pedidoAtualizar = pedido.get();

                pedidoAtualizar.setIdPagamentoMP(idPagamento);

                pedidoAtualizar.setQrCode(qrImage.getBytes());

                pedidoAtualizar.setCodigoPix(qrCopiaCola);

                pedidoService.confirmarPagamento(pedidoAtualizar);
            }
        }
    }

    private String removeAspas(String palavra) {
        return palavra.replace("\"", "");
    }


}
