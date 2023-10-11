package com.tech.challenge.soat.core.domain;


import com.tech.challenge.soat.core.enumerator.StatusPagamento;
import com.tech.challenge.soat.core.enumerator.StatusPedido;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
public class Pedido {

    @Id
    private UUID id;

    @ManyToMany
    @JoinTable(
            name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id", referencedColumnName = "id")
    )
    private List<Produto> produtos;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    private StatusPedido statusPedido;

    private Double preco;

    private StatusPagamento statusPagamento;

    private LocalTime tempoPreparo;

    public void adicionarProdutoAoPedido(Produto produto){
        this.produtos.add(produto);
        this.preco = this.preco + produto.getPreco().doubleValue();
    }
}
