package com.tech.challenge.soat.adapters.driver.v1;

import com.tech.challenge.soat.adapters.driver.v1.model.request.ProdutoRequest;
import com.tech.challenge.soat.adapters.driver.v1.model.response.ProdutoResponse;
import com.tech.challenge.soat.adapters.mapper.ProdutoMapper;
import com.tech.challenge.soat.core.applications.service.ProdutoService;
import com.tech.challenge.soat.core.domain.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoMapper produtoMapper;

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrarProduto(@RequestBody ProdutoRequest produtoRequest) throws Exception {
        Produto produto = produtoService.salvar(produtoRequest);
        ProdutoResponse produtoResponse = produtoMapper.toResponse(produto);
        return new ResponseEntity<>(produtoResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> getProdutos() {
        List<Produto> list = produtoService.findAll();
        List<ProdutoResponse> produtos = produtoMapper.getProdutos(list);
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<ProdutoResponse> getProduto(@PathVariable UUID produtoId) {
        Produto produto = produtoService.getById(produtoId);
        ProdutoResponse produtoResponse = produtoMapper.toResponse(produto);
        return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
    }

}
