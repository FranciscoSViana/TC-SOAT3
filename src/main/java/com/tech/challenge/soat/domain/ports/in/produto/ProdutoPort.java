package com.tech.challenge.soat.domain.ports.in.produto;

import com.tech.challenge.soat.adapters.models.in.ProdutoRequest;
import com.tech.challenge.soat.adapters.models.out.ProdutoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface ProdutoPort {

    ResponseEntity<ProdutoResponse> cadastrarProduto(@RequestBody ProdutoRequest produtoRequest) throws Exception;


    ResponseEntity<List<ProdutoResponse>> getProdutos();


    ResponseEntity<ProdutoResponse> getProduto(@PathVariable UUID produtoId);
}
