package com.tech.challenge.soat.adapters.controllers;

import com.tech.challenge.soat.adapters.models.in.ProdutoRequest;
import com.tech.challenge.soat.adapters.models.out.ProdutoContentResponse;
import com.tech.challenge.soat.adapters.models.out.ProdutoResponse;
import com.tech.challenge.soat.domain.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/produtos")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrarProduto(@RequestBody ProdutoRequest produtoRequest) {
        ProdutoResponse produtoResponse = produtoService.salvar(produtoRequest);
        return new ResponseEntity<>(produtoResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ProdutoContentResponse> getProdutos(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {

        ProdutoContentResponse response = produtoService.buscarTodas(pageNumber, pageSize);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<ProdutoResponse> getProduto(@PathVariable UUID produtoId) {

        ProdutoResponse produtoResponse = produtoService.buscarPorId(produtoId);

        return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
    }


    @PatchMapping("/atualizar")
    public ResponseEntity<ProdutoResponse> atualizarProduto(@RequestBody ProdutoRequest produtoRequest) {

        ProdutoResponse produtoResponse = produtoService.atualizar(produtoRequest);

        return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{produtoId}")
    public ResponseEntity<ProdutoResponse> deleteProduto(@PathVariable UUID produtoId) {

        ProdutoResponse produtoResponse = produtoService.delete(produtoId);

        return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
    }

}
