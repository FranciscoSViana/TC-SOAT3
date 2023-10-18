package com.tech.challenge.soat.adapters.factory;

import com.tech.challenge.soat.adapters.models.in.ProdutoRequest;
import com.tech.challenge.soat.domain.enums.TipoCategoria;
import com.tech.challenge.soat.domain.exceptions.NegocioException;
import com.tech.challenge.soat.domain.models.ProdutoModel;
import com.tech.challenge.soat.domain.providers.DataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProdutoFactory {

    private final DataProvider dataProvider;


    public ProdutoModel novo(ProdutoRequest produtoRequest) {

        if (Objects.isNull(produtoRequest)) {
            throw new NegocioException("Produto não pode ser nulo");
        }

        return ProdutoModel.
                builder()
                .id(UUID.randomUUID())
                .nome(produtoRequest.getNome())
                .preco(produtoRequest.getPreco())
                .dataHoraCriacao(dataProvider.obterDataHoraAtual())
                .descricao(produtoRequest.getDescricao())
                .imagem(Base64.getDecoder().decode(produtoRequest.getImagemBase64()))
                .categoria(TipoCategoria.fromName(produtoRequest.getCategoria()))
                .status(Boolean.TRUE)
                .build();
    }

    public ProdutoModel atualizar(ProdutoRequest produtoRequest, ProdutoModel produtoModel) {

        if (Objects.isNull(produtoRequest)) {
            throw new NegocioException("Produto não pode ser nulo");
        }

        produtoModel.setNome(produtoRequest.getNome());
        produtoModel.setPreco(produtoRequest.getPreco());
        produtoModel.setDescricao(produtoRequest.getDescricao());
        produtoModel.setImagem(Base64.getDecoder().decode(produtoRequest.getImagemBase64()));
        produtoModel.setCategoria(TipoCategoria.fromName(produtoRequest.getCategoria()));
        produtoModel.setDataHoraAlteracao(dataProvider.obterDataHoraAtual());
        return produtoModel;
    }

    public ProdutoModel delete(ProdutoModel produtoModel) {

        if (Objects.isNull(produtoModel)) {
            throw new NegocioException("Produto não pode ser nulo");
        }

        produtoModel.setDataHoraAlteracao(dataProvider.obterDataHoraAtual());
        produtoModel.setStatus(Boolean.FALSE);
        return produtoModel;
    }
}
