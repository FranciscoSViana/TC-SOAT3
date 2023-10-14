package com.tech.challenge.soat.adapters.mapper;

import com.tech.challenge.soat.adapters.driver.v1.model.response.PedidoResponse;
import com.tech.challenge.soat.domain.models.PedidoModel;
import com.tech.challenge.soat.domain.models.ProdutoModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoMapper {
    public PedidoResponse pedidoToPedidoRespose(PedidoModel pedido){
        return PedidoResponse
                .builder()
                .id(pedido.getId())
                .cliente(pedido.getCliente().getId())
                .preco(pedido.getPreco())
                .statusPedido(pedido.getStatusPedido())
                .produtos(pedido.getProdutos().stream().map(ProdutoModel::getId).toList())
                .statusPagamento(pedido.getStatusPagamento())
                .tempoPreparo(pedido.getTempoPreparo())
                .build();
    }

    public List<PedidoResponse> pedidosToPedidosRespose(List<PedidoModel> pedidos) {
        return pedidos.stream().map((this::pedidoToPedidoRespose)).toList();
    }
}
