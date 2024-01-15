package com.tech.challenge.soat.adapters.mapper;

import com.tech.challenge.soat.adapters.models.in.PedidoRequest;
import com.tech.challenge.soat.adapters.models.out.PedidoResponse;
import com.tech.challenge.soat.domain.enums.StatusPagamento;
import com.tech.challenge.soat.domain.enums.StatusPedido;
import com.tech.challenge.soat.domain.models.PedidoModel;
import com.tech.challenge.soat.domain.models.ProdutoModel;
import com.tech.challenge.soat.domain.services.ClienteService;
import com.tech.challenge.soat.domain.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PedidoMapper {

    private final ProdutoService produtoService;

    private final ClienteService clienteService;

    public PedidoResponse pedidoToPedidoRespose(PedidoModel pedido) {
        return PedidoResponse
                .builder()
                .id(pedido.getId())
                .cliente(pedido.getCliente().getId())
                .preco(pedido.getPreco())
                .statusPedido(pedido.getStatusPedido())
                .produtos(pedido.getProdutos().stream().map(ProdutoModel::getId).toList())
                .statusPagamento(pedido.getStatusPagamento())
                .tempoPreparo(pedido.getTempoPreparo())
                .codigoPix(pedido.getCodigoPix())
                .idPagamentoMP(pedido.getIdPagamentoMP())
                .build();
    }

    public PedidoModel pedidoRequestToPedidoModel(PedidoRequest pedidoRequest) {

        UUID id = (pedidoRequest.getId() != null) ? pedidoRequest.getId() : UUID.randomUUID();

        return PedidoModel
                .builder()
                .id(id)
                .tempoPreparo(pedidoRequest.getTempoPreparo())
                .cliente(clienteService.buscarOuFalhar(pedidoRequest.getCliente()))
                .statusPedido(StatusPedido.RECEBIDO)
                .produtos(pedidoRequest.getProdutos()
                        .parallelStream()
                        .map(produtoId -> produtoService.obterProdutoPorUUID(produtoId)).toList())
                .statusPagamento(StatusPagamento.AGUARDANDO_PAGAMENTO)
                .build();
    }

    public List<PedidoResponse> pedidosToPedidosRespose(List<PedidoModel> pedidos) {
        return pedidos.stream().map((this::pedidoToPedidoRespose)).toList();
    }
}
