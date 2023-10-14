package com.tech.challenge.soat.adapters.models.in;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoRequest {

    private String nome;
    private String descricao;
    private String categoria;
    private BigDecimal preco;
    private String imagemBase64;

}
