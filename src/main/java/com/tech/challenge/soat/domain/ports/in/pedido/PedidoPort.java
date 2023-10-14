package com.tech.challenge.soat.domain.ports.in.pedido;

import com.tech.challenge.soat.adapters.models.in.AlterarStatusPedidoRequest;
import com.tech.challenge.soat.adapters.models.in.PedidoRequest;
import com.tech.challenge.soat.adapters.models.out.PedidoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface PedidoPort {

    ResponseEntity<PedidoResponse> cadastrarPedido(@RequestBody PedidoRequest request);


    ResponseEntity<PedidoResponse> pagarPedido(@PathVariable UUID id);


    ResponseEntity<PedidoResponse> alterarStatus(@PathVariable UUID id,
                                                 @RequestBody AlterarStatusPedidoRequest request);


    ResponseEntity<PedidoResponse> buscarPedido(@PathVariable UUID id);


    ResponseEntity<List<PedidoResponse>> buscarPedidos();

    public ResponseEntity<PedidoResponse> criarPagamento(@PathVariable UUID id);

}
