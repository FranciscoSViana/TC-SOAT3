package com.tech.challenge.soat.core.applications.factory;

import com.tech.challenge.soat.adapters.driver.v1.model.request.ProdutoRequest;
import com.tech.challenge.soat.domain.models.ProdutoModel;

@FunctionalInterface
public interface ProdutoFactory {

    ProdutoModel novo(ProdutoRequest produtoRequest);
}
