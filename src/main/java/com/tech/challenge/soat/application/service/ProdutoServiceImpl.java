package com.tech.challenge.soat.application.service;

import com.tech.challenge.soat.adapters.models.in.ProdutoRequest;
import com.tech.challenge.soat.domain.exceptions.NegocioException;
import com.tech.challenge.soat.domain.factory.ProdutoFactory;
import com.tech.challenge.soat.domain.models.ProdutoModel;
import com.tech.challenge.soat.domain.repositories.ProdutoRepository;
import com.tech.challenge.soat.domain.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoFactory produtoFactory;

    private final ProdutoRepository produtoRepository;

    @Override
    public ProdutoModel salvar(ProdutoRequest produtoRequest) {
        ProdutoModel produto = produtoFactory.novo(produtoRequest);
        return produtoRepository.save(produto);
    }

    @Override
    public List<ProdutoModel> findAll() {
        return produtoRepository.findAll();
    }

    @Override
    public ProdutoModel getById(UUID uuid) {
       return produtoRepository.findById(uuid).orElseThrow(() -> new NegocioException("Produto não encontrado"));
    }
}
