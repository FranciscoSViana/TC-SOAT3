package com.tech.challenge.soat.core.applications.factory;

import com.tech.challenge.soat.adapters.driver.v1.model.request.ProdutoRequest;
import com.tech.challenge.soat.core.domain.Produto;

@FunctionalInterface
public interface ProdutoFactory {

    Produto novo(ProdutoRequest produtoRequest);
}
