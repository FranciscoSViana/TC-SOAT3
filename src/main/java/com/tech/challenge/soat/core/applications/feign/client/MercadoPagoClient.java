package com.tech.challenge.soat.core.applications.feign.client;

import com.tech.challenge.soat.core.applications.feign.client.request.PagamentoRequest;
import com.tech.challenge.soat.core.applications.feign.client.response.PagamentoResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "mercadoPagoClient", url = "${mp.urlbase}")
public interface MercadoPagoClient {

    @RequestMapping(method = RequestMethod.POST, value = "/v1/payments", consumes = "application/json", produces = "application/json")
    String criarPagamento(@RequestHeader("Authorization") String authorization,
                          @Valid @RequestBody PagamentoRequest pagamentoRequest);


}
