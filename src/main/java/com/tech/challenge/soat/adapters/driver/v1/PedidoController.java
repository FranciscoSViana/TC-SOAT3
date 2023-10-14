package com.tech.challenge.soat.adapters.driver.v1;


import com.tech.challenge.soat.adapters.driver.v1.model.request.AlterarStatusPedidoRequest;
import com.tech.challenge.soat.adapters.driver.v1.model.request.PedidoRequest;
import com.tech.challenge.soat.adapters.driver.v1.model.response.PedidoResponse;
import com.tech.challenge.soat.adapters.mapper.PedidoMapper;
import com.tech.challenge.soat.domain.services.PedidoService;
import com.tech.challenge.soat.domain.models.PedidoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;
    @Autowired
    PedidoMapper pedidoMapper;

    @PostMapping
    public ResponseEntity<PedidoResponse> cadastrarPedido(@RequestBody PedidoRequest request) {
        PedidoModel pedido = pedidoService.salvar(request);
        return ResponseEntity.ok(pedidoMapper.pedidoToPedidoRespose(pedido));
    }

    @PostMapping("/{id}/pagar")
    public ResponseEntity<PedidoResponse> pagarPedido(@PathVariable UUID id) {
        PedidoModel pedido = pedidoService.pagar(id);
        return ResponseEntity.ok(pedidoMapper.pedidoToPedidoRespose(pedido));
    }

    @PostMapping("/{id}/alterar-status")
    public ResponseEntity<PedidoResponse> alterarStatus(@PathVariable UUID id,
                                                        @RequestBody AlterarStatusPedidoRequest request) {
        PedidoModel pedido = pedidoService.alterarStatus(id, request.getStatus());
        return ResponseEntity.ok(pedidoMapper.pedidoToPedidoRespose(pedido));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscarPedido(@PathVariable UUID id) {
        PedidoModel pedido = pedidoService.buscarPedido(id);
        return ResponseEntity.ok(pedidoMapper.pedidoToPedidoRespose(pedido));
    }
    @GetMapping
    public ResponseEntity<List<PedidoResponse>> buscarPedidos() {
        List<PedidoModel> pedido = pedidoService.buscarPedidos();
        return ResponseEntity.ok(pedidoMapper.pedidosToPedidosRespose(pedido));
    }
}
