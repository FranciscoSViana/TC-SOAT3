package com.tech.challenge.soat.core.applications.service;

import com.tech.challenge.soat.adapters.driver.v1.model.request.ProdutoRequest;
import com.tech.challenge.soat.core.domain.Produto;

import java.util.List;
import java.util.UUID;

public interface ProdutoService {

    Produto salvar(ProdutoRequest produtoRequest);

    List<Produto> findAll();

    Produto getById(UUID uuid);
}
