package com.tech.challenge.soat.domain.services;

import com.tech.challenge.soat.adapters.driver.v1.model.request.ProdutoRequest;
import com.tech.challenge.soat.domain.models.ProdutoModel;

import java.util.List;
import java.util.UUID;

public interface ProdutoService {

    ProdutoModel salvar(ProdutoRequest produtoRequest);

    List<ProdutoModel> findAll();

    ProdutoModel getById(UUID uuid);
}
