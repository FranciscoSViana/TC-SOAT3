package com.tech.challenge.soat.core.applications.feign.client;

import com.tech.challenge.soat.core.applications.feign.client.request.PagamentoRequest;
import com.tech.challenge.soat.core.applications.feign.client.response.PagamentoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "mercadoPagoClient", url = "${mp.urlbase}")
public interface MercadoPagoClient {

    @PostMapping("/v1/payments")
    String criarPagamento(@RequestHeader("Authorization") String authorization,
                                     @RequestHeader("Accept") String accept,
                                     @RequestHeader("Content-Type") String contentType,
                                     @RequestBody PagamentoRequest pagamentoRequest);


}
