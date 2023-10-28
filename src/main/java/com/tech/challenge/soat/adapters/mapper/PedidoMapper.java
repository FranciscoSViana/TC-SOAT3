package com.tech.challenge.soat.adapters.mapper;

import com.tech.challenge.soat.adapters.models.in.PedidoRequest;
import com.tech.challenge.soat.adapters.models.out.PedidoResponse;
import com.tech.challenge.soat.domain.enums.StatusPagamento;
import com.tech.challenge.soat.domain.enums.StatusPedido;
import com.tech.challenge.soat.domain.models.PedidoModel;
import com.tech.challenge.soat.domain.models.ProdutoModel;
import com.tech.challenge.soat.domain.services.ClienteService;
import com.tech.challenge.soat.domain.services.PedidoService;
import com.tech.challenge.soat.domain.services.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class PedidoMapper {

    PedidoService pedidoService;
    ProdutoService produtoService;
    ClienteService clienteService;

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
                .qrCode(new String(pedido.getQrCode(), StandardCharsets.UTF_8))
                .codigoPix(pedido.getCodigoPix())
                .build();
    }

    public PedidoModel pedidoRequestToPedidoModel(PedidoRequest pedidoRequest){
        UUID id = (pedidoRequest.getId() != null) ? pedidoRequest.getId() : UUID.randomUUID();

        return  PedidoModel
                .builder()
                .id(id)
                .tempoPreparo(pedidoRequest.getTempoPreparo())
                .cliente(clienteService.buscarOuFalhar(pedidoRequest.getCliente()))
                .statusPedido(StatusPedido.RECEBIDO)
                .produtos(pedidoRequest.getProdutos().parallelStream().map((produtoId)-> produtoService.buscarPorId(produtoId)).toList())
                .statusPagamento(StatusPagamento.AGUARDANDO_PAGAMENTO)
                .build();
    }



    public List<PedidoResponse> pedidosToPedidosRespose(List<PedidoModel> pedidos) {
        return pedidos.stream().map((this::pedidoToPedidoRespose)).toList();
    }
}
