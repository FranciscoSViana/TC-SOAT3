package com.tech.challenge.soat.adapters.controllers;

import com.tech.challenge.soat.adapters.factory.ProdutoFactory;
import com.tech.challenge.soat.adapters.mapper.ProdutoMapper;
import com.tech.challenge.soat.adapters.models.in.ProdutoRequest;
import com.tech.challenge.soat.adapters.models.out.ProdutoResponse;
import com.tech.challenge.soat.domain.models.ProdutoModel;
import com.tech.challenge.soat.domain.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/produtos")
public class ProdutoController  {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoMapper produtoMapper;
    @Autowired
    private ProdutoFactory produtoFactory;


    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrarProduto(@RequestBody ProdutoRequest produtoRequest) throws Exception {
        ProdutoModel produto = produtoService.salvar(produtoFactory.novo(produtoRequest));
        ProdutoResponse produtoResponse = produtoMapper.produtoToProdutoResponse(produto);
        return new ResponseEntity<>(produtoResponse, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> getProdutos() {
        List<ProdutoModel> list = produtoService.buscarTodas();
        List<ProdutoResponse> produtos = produtoMapper.getProdutos(list);
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }


    @GetMapping("/{produtoId}")
    public ResponseEntity<ProdutoResponse> getProduto(@PathVariable UUID produtoId) {
        ProdutoModel produto = produtoService.buscarPorId(produtoId);
        ProdutoResponse produtoResponse = produtoMapper.produtoToProdutoResponse(produto);
        return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
    }

}
