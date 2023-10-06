package com.tech.challenge.soat.core.applications.service;

import com.tech.challenge.soat.adapters.driver.v1.model.request.ProdutoRequest;
import com.tech.challenge.soat.adapters.driver.v1.model.response.ProdutoResponse;
import com.tech.challenge.soat.adapters.mapper.ProdutoMapper;
import com.tech.challenge.soat.core.applications.factory.ProdutoFactory;
import com.tech.challenge.soat.core.applications.ports.ProdutoRepository;
import com.tech.challenge.soat.core.domain.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoFactory produtoFactory;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto salvar(ProdutoRequest produtoRequest) {
        Produto produto = produtoFactory.novo(produtoRequest);
        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }
}
