package com.tech.challenge.soat.core.applications.service;

import com.tech.challenge.soat.adapters.driver.v1.model.request.ProdutoRequest;
import com.tech.challenge.soat.adapters.driver.v1.model.response.ProdutoResponse;
import com.tech.challenge.soat.adapters.mapper.ProdutoMapper;
import com.tech.challenge.soat.core.applications.factory.ProdutoFactory;
import com.tech.challenge.soat.core.applications.ports.ProdutoRepository;
import com.tech.challenge.soat.core.domain.Produto;
import com.tech.challenge.soat.core.exception.NegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoFactory produtoFactory;

    private final ProdutoRepository produtoRepository;

    @Override
    public Produto salvar(ProdutoRequest produtoRequest) {
        Produto produto = produtoFactory.novo(produtoRequest);
        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto getById(UUID uuid) {
       return produtoRepository.findById(uuid).orElseThrow(() -> new NegocioException("Produto não encontrado"));
    }
}
