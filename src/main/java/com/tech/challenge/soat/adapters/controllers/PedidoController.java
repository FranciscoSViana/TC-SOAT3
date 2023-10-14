package com.tech.challenge.soat.adapters.controllers;


import com.tech.challenge.soat.adapters.mapper.PedidoMapper;
import com.tech.challenge.soat.adapters.models.in.AlterarStatusPedidoRequest;
import com.tech.challenge.soat.adapters.models.in.PedidoRequest;
import com.tech.challenge.soat.adapters.models.out.PedidoResponse;
import com.tech.challenge.soat.domain.models.PedidoModel;
import com.tech.challenge.soat.domain.ports.in.pedido.PedidoPort;
import com.tech.challenge.soat.domain.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/pedidos")
public class PedidoController implements PedidoPort {

    @Autowired
    PedidoService pedidoService;
    @Autowired
    PedidoMapper pedidoMapper;

    @Override
    @PostMapping
    public ResponseEntity<PedidoResponse> cadastrarPedido(@RequestBody PedidoRequest request) {
        PedidoModel pedido = pedidoService.salvar(request);
        return ResponseEntity.ok(pedidoMapper.pedidoToPedidoRespose(pedido));
    }

    @Override
    @PostMapping("/{id}/pagar")
    public ResponseEntity<PedidoResponse> pagarPedido(@PathVariable UUID id) {
        PedidoModel pedido = pedidoService.pagar(id);
        return ResponseEntity.ok(pedidoMapper.pedidoToPedidoRespose(pedido));
    }

    @Override
    @PostMapping("/{id}/alterar-status")
    public ResponseEntity<PedidoResponse> alterarStatus(@PathVariable UUID id,
                                                        @RequestBody AlterarStatusPedidoRequest request) {
        PedidoModel pedido = pedidoService.alterarStatus(id, request.getStatus());
        return ResponseEntity.ok(pedidoMapper.pedidoToPedidoRespose(pedido));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscarPedido(@PathVariable UUID id) {
        PedidoModel pedido = pedidoService.buscarPedido(id);
        return ResponseEntity.ok(pedidoMapper.pedidoToPedidoRespose(pedido));
    }
    @Override
    @GetMapping
    public ResponseEntity<List<PedidoResponse>> buscarPedidos() {
        List<PedidoModel> pedido = pedidoService.buscarPedidos();
        return ResponseEntity.ok(pedidoMapper.pedidosToPedidosRespose(pedido));
    }

    @Override
    @PostMapping("/{id}/criar-pagamento")
    public ResponseEntity<PedidoResponse> criarPagamento(@PathVariable UUID id) {
        PedidoModel pedido = pedidoService.criarPagamento(id);
        return ResponseEntity.ok(pedidoMapper.pedidoToPedidoRespose(pedido));
    }
}
