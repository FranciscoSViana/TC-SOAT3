package com.tech.challenge.soat.adapters.mapper;

import com.tech.challenge.soat.adapters.driver.v1.model.response.PedidoResponse;
import com.tech.challenge.soat.core.domain.Pedido;
import com.tech.challenge.soat.core.domain.Produto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoMapper {
    public PedidoResponse pedidoToPedidoRespose(Pedido pedido){
        return PedidoResponse
                .builder()
                .id(pedido.getId())
                .cliente(pedido.getCliente().getId())
                .preco(pedido.getPreco())
                .statusPedido(pedido.getStatusPedido())
                .produtos(pedido.getProdutos().stream().map(Produto::getId).toList())
                .statusPagamento(pedido.getStatusPagamento())
                .tempoPreparo(pedido.getTempoPreparo())
                .build();
    }

    public List<PedidoResponse> pedidosToPedidosRespose(List<Pedido> pedidos) {
        return pedidos.stream().map((this::pedidoToPedidoRespose)).toList();
    }
}
