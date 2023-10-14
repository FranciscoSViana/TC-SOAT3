package com.tech.challenge.soat.infra.client;

import com.tech.challenge.soat.adapters.models.out.PagamentoMPRequest;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "mercadoPagoClient", url = "${mp.urlbase}")
public interface MercadoPagoClient {

    @RequestMapping(method = RequestMethod.POST, value = "/v1/payments", consumes = "application/json", produces = "application/json")
    String criarPagamento(@RequestHeader("Authorization") String authorization,
                          @Valid @RequestBody PagamentoMPRequest pagamentoRequest);


}
