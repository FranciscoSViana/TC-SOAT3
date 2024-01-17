package com.tech.challenge.soat.infra.client;

import com.tech.challenge.soat.adapters.models.out.PagamentoMPRequest;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "mercadoPagoClient", url = "${mp.urlbase}")
public interface MercadoPagoClient {

    @PostMapping(value = "/v1/payments", consumes = "application/json", produces = "application/json")
    String criarPagamento(@RequestHeader("Authorization") String authorization,
                          @Valid @RequestBody PagamentoMPRequest pagamentoRequest);

    @GetMapping(value = "/v1/payments/{payment_id}", consumes = "application/json", produces = "application/json")
    String confirmarPagamento(@RequestHeader("Authorization") String authorization,
                              @Valid @PathVariable Long payment_id);


}
