package com.tech.challenge.soat.application.service;

import com.tech.challenge.soat.domain.models.PedidoModel;
import com.tech.challenge.soat.domain.services.PedidoService;
import com.tech.challenge.soat.domain.services.WebhookMPService;
import com.tech.challenge.soat.domain.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class WebhookMPServiceImpl implements WebhookMPService {

    private final JsonUtil jsonUtil;

    private final PedidoService pedidoService;

    @Override
    public void integracaoMP(String payload) {

        String data = jsonUtil.obterValorChaveJson(payload, "data");

        String idPagamentoMP = jsonUtil.obterValorChaveJson(data, "id");

        if(!idPagamentoMP.isBlank()){

           PedidoModel pedido = pedidoService.obterPorIdPagamentoMP(idPagamentoMP);

            pedidoService.confirmarPagamento(pedido);

        }



    }
}
