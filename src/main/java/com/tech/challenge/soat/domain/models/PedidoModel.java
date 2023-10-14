package com.tech.challenge.soat.domain.models;


import com.tech.challenge.soat.core.enumerator.StatusPagamento;
import com.tech.challenge.soat.core.enumerator.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoModel {

    private UUID id;

    private List<ProdutoModel> produtos;

    private ClienteModel cliente;

    private StatusPedido statusPedido;

    private Double preco;

    private StatusPagamento statusPagamento;

    private LocalTime tempoPreparo;

    public void adicionarProdutoAoPedido(ProdutoModel produto){
        this.produtos.add(produto);
        this.preco = this.preco + produto.getPreco().doubleValue();
    }
}
