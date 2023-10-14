package com.tech.challenge.soat.domain.factory;

import com.tech.challenge.soat.adapters.models.in.ProdutoRequest;
import com.tech.challenge.soat.domain.models.ProdutoModel;

@FunctionalInterface
public interface ProdutoFactory {

    ProdutoModel novo(ProdutoRequest produtoRequest);
}
