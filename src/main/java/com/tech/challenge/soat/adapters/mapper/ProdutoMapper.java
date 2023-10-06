package com.tech.challenge.soat.adapters.mapper;

import com.tech.challenge.soat.adapters.driver.v1.model.request.ProdutoRequest;
import com.tech.challenge.soat.adapters.driver.v1.model.response.ProdutoResponse;
import com.tech.challenge.soat.core.domain.Produto;
import com.tech.challenge.soat.core.util.CategoriaUtil;
import com.tech.challenge.soat.core.util.ImagemUtil;
import com.tech.challenge.soat.shared.util.provider.UUIDProviderImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class ProdutoMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ImagemUtil imagemUtil;

    public Produto toDomain(ProdutoRequest produtoRequest) throws Exception {
       return modelMapper.map(produtoRequest, Produto.class);
    }

    public ProdutoResponse toResponse(Produto produto) {
        ProdutoResponse produtoResponse = new ProdutoResponse();
        produtoResponse.setUuid(produto.getId());
        produtoResponse.setCategoria(produto.getCategoria().getDescricao());
        produtoResponse.setDescricao(produto.getDescricao());
        produtoResponse.setNome(produto.getNome());
        produtoResponse.setPreco(produto.getPreco());
        produtoResponse.setImagemBase64(imagemUtil.encodeBase64(produto.getImagem()));
        return produtoResponse;
    }

    public List<ProdutoResponse> getProdutos(List<Produto> list) {
        List<ProdutoResponse> produtos = new ArrayList<>();
        list.forEach(item -> {
            ProdutoResponse produtoResponse = toResponse(item);
            produtos.add(produtoResponse);
        });

        return produtos;
    }

}
