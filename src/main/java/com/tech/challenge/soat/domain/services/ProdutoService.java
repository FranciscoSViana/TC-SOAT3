package com.tech.challenge.soat.domain.services;

import com.tech.challenge.soat.domain.models.ProdutoModel;

import java.util.List;
import java.util.UUID;

public interface ProdutoService {

    ProdutoModel salvar(ProdutoModel produto);

    List<ProdutoModel> buscarTodas(int pageNumber, int pageSize);

    ProdutoModel buscarPorId(UUID uuid);

    ProdutoModel atualizar(ProdutoModel produto);

    ProdutoModel delete(ProdutoModel produto);
}
