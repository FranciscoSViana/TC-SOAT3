package com.tech.challenge.soat.adapters.controllers;

import com.tech.challenge.soat.adapters.mapper.ProdutoMapper;
import com.tech.challenge.soat.adapters.models.in.ProdutoRequest;
import com.tech.challenge.soat.adapters.models.out.ProdutoResponse;
import com.tech.challenge.soat.domain.models.ProdutoModel;
import com.tech.challenge.soat.domain.ports.in.produto.ProdutoPort;
import com.tech.challenge.soat.domain.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/produtos")
public class ProdutoController implements ProdutoPort {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoMapper produtoMapper;

    @Override
    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrarProduto(@RequestBody ProdutoRequest produtoRequest) throws Exception {
        ProdutoModel produto = produtoService.salvar(produtoRequest);
        ProdutoResponse produtoResponse = produtoMapper.produtoToProdutoResponse(produto);
        return new ResponseEntity<>(produtoResponse, HttpStatus.CREATED);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> getProdutos() {
        List<ProdutoModel> list = produtoService.findAll();
        List<ProdutoResponse> produtos = produtoMapper.getProdutos(list);
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{produtoId}")
    public ResponseEntity<ProdutoResponse> getProduto(@PathVariable UUID produtoId) {
        ProdutoModel produto = produtoService.getById(produtoId);
        ProdutoResponse produtoResponse = produtoMapper.produtoToProdutoResponse(produto);
        return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
    }

}
