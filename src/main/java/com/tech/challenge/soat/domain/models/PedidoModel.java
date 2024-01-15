package com.tech.challenge.soat.domain.models;


import com.tech.challenge.soat.domain.enums.StatusPagamento;
import com.tech.challenge.soat.domain.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoModel {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private ClienteModel cliente;

    private StatusPedido statusPedido;

    private BigDecimal preco;

    private StatusPagamento statusPagamento;

    private LocalTime tempoPreparo;

    private String codigoPix;

    private String idPagamentoMP;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "LONGBLOB")
    private byte[] qrCode;

    @ManyToMany
    @JoinTable(
            name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id", referencedColumnName = "id")
    )

    private List<ProdutoModel> produtos;

    public void adicionarProdutoAoPedido(ProdutoModel produto){
        this.produtos.add(produto);
        this.preco = this.preco.add(produto.getPreco()) ;
    }
}
