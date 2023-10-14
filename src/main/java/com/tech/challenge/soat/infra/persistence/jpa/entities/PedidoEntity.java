package com.tech.challenge.soat.infra.persistence.jpa.entities;


import com.tech.challenge.soat.core.enumerator.StatusPagamento;
import com.tech.challenge.soat.core.enumerator.StatusPedido;
import com.tech.challenge.soat.domain.models.ClienteModel;
import com.tech.challenge.soat.domain.models.ProdutoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntity {

    @Id
    private UUID id;

    @ManyToMany
    @JoinTable(
            name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id", referencedColumnName = "id")
    )
    private List<ProdutoModel> produtos;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
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
