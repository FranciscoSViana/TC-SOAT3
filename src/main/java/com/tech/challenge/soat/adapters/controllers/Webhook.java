package com.tech.challenge.soat.adapters.controllers;

import com.tech.challenge.soat.domain.models.PedidoModel;
import com.tech.challenge.soat.domain.ports.out.pagamento.PagamentoPort;
import com.tech.challenge.soat.domain.services.PedidoService;
import com.tech.challenge.soat.domain.utils.JsonUtil;
import com.tech.challenge.soat.infra.client.MercadoPagoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class Webhook {

    private final JsonUtil jsonUtil;

    private final PedidoService service;


    @PostMapping("/mp")
    public String getNgrokUrl(@RequestBody String payload) {

        String data = jsonUtil.obterValorChaveJson(payload, "data");

        String id = jsonUtil.obterValorChaveJson(payload, "id");

        PedidoModel pedido = service.obterPorIdPagamentoMP(id);



        return "Endereço fornecido pelo ngrok: " + id;
    }


}
