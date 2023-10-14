package com.tech.challenge.soat.adapters.driver.v1;

import com.tech.challenge.soat.adapters.driver.v1.model.request.PagamentoMPRequest;
import com.tech.challenge.soat.core.applications.feign.client.request.PagamentoRequest;
import com.tech.challenge.soat.core.applications.feign.client.response.PagamentoResponse;
import com.tech.challenge.soat.core.applications.feign.service.MercadoPagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/pagamentos")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PagamentoController {

    private final MercadoPagoService mercadoPagoService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PagamentoResponse> criarPagamento(@RequestBody @Valid PagamentoMPRequest pagamento) {

        return ResponseEntity.ok(mercadoPagoService.criarPagamento(pagamento));
    }
}
