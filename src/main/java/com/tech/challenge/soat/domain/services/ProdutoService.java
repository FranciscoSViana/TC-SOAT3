package com.tech.challenge.soat.domain.services;

import com.tech.challenge.soat.adapters.models.in.ProdutoRequest;
import com.tech.challenge.soat.adapters.models.out.ProdutoContentResponse;
import com.tech.challenge.soat.adapters.models.out.ProdutoResponse;
import com.tech.challenge.soat.domain.models.ProdutoModel;

import java.util.List;
import java.util.UUID;

public interface ProdutoService {

    ProdutoResponse salvar(ProdutoRequest produtoRequest);

    ProdutoContentResponse buscarTodas(int pageNumber, int pageSize);

    ProdutoResponse buscarPorId(UUID uuid);

    ProdutoResponse atualizar(ProdutoRequest produtoRequest);

    ProdutoResponse delete(UUID produtoId);

    ProdutoModel obterProdutoPorUUID(UUID produtoId);
}
