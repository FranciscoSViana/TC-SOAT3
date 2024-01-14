package com.tech.challenge.soat.adapters.controllers;

import com.tech.challenge.soat.adapters.factory.ProdutoFactory;
import com.tech.challenge.soat.adapters.mapper.ProdutoMapper;
import com.tech.challenge.soat.adapters.models.in.ProdutoRequest;
import com.tech.challenge.soat.adapters.models.out.ProdutoContentResponse;
import com.tech.challenge.soat.adapters.models.out.ProdutoResponse;
import com.tech.challenge.soat.domain.models.ProdutoModel;
import com.tech.challenge.soat.domain.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/produtos")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProdutoController  {

    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper;
    private final ProdutoFactory produtoFactory;


    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrarProduto(@RequestBody ProdutoRequest produtoRequest) throws Exception {
        ProdutoModel produto = produtoService.salvar(produtoFactory.novo(produtoRequest));
        ProdutoResponse produtoResponse = produtoMapper.produtoToProdutoResponse(produto);
        return new ResponseEntity<>(produtoResponse, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<ProdutoContentResponse> getProdutos(@RequestParam(defaultValue = "0") int pageNumber,
                                                              @RequestParam(defaultValue = "10") int pageSize) {
        List<ProdutoModel> list = produtoService.buscarTodas(pageNumber, pageSize);
        List<ProdutoResponse> produtos = produtoMapper.getProdutos(list);
        ProdutoContentResponse response = new ProdutoContentResponse(produtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{produtoId}")
    public ResponseEntity<ProdutoResponse> getProduto(@PathVariable UUID produtoId) {
        ProdutoModel produto = produtoService.buscarPorId(produtoId);
        ProdutoResponse produtoResponse = produtoMapper.produtoToProdutoResponse(produto);
        return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
    }


    @PatchMapping("/atualizar")
    public ResponseEntity<ProdutoResponse> atualizarProduto(@RequestBody ProdutoRequest produtoRequest) throws Exception {
        ProdutoModel produtoModel = produtoService.buscarPorId(produtoRequest.getUuid());
        ProdutoModel newProdutoModel = produtoFactory.atualizar(produtoRequest, produtoModel);
        ProdutoModel produto = produtoService.salvar(newProdutoModel);
        ProdutoResponse produtoResponse = produtoMapper.produtoToProdutoResponse(produto);
        return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{produtoId}")
    public ResponseEntity<ProdutoResponse> deleteProduto(@PathVariable UUID produtoId) {
        ProdutoModel produto = produtoService.buscarPorId(produtoId);
        ProdutoModel newProduto = produtoService.delete(produtoFactory.delete(produto));
        ProdutoResponse produtoResponse = produtoMapper.produtoToProdutoResponse(newProduto);
        return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
    }

}
