package com.tech.challenge.soat.adapters.mapper;

import com.tech.challenge.soat.adapters.driver.v1.model.request.ProdutoRequest;
import com.tech.challenge.soat.adapters.driver.v1.model.response.ProdutoResponse;
import com.tech.challenge.soat.core.domain.Produto;
import com.tech.challenge.soat.core.util.ImagemUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProdutoMapper {

    private final ModelMapper modelMapper;

    private final ImagemUtil imagemUtil;

    public Produto produtoRequestToProduto(ProdutoRequest produtoRequest) {
       return modelMapper.map(produtoRequest, Produto.class);
    }

    public ProdutoResponse produtoToProdutoResponse(Produto produto) {
        return ProdutoResponse.builder()
                .uuid(produto.getId())
                .categoria(produto.getCategoria().getDescricao())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .nome(produto.getNome())
                .imagemBase64(imagemUtil.encodeBase64(produto.getImagem()))
                .build();
    }

    public List<ProdutoResponse> getProdutos(List<Produto> list) {
        List<ProdutoResponse> produtos = new ArrayList<>();
        list.forEach(item -> {
            ProdutoResponse produtoResponse = produtoToProdutoResponse(item);
            produtos.add(produtoResponse);
        });

        return produtos;
    }

}
