package com.tech.challenge.soat.application.factory;

import com.tech.challenge.soat.adapters.models.in.ProdutoRequest;
import com.tech.challenge.soat.domain.enums.TipoCategoria;
import com.tech.challenge.soat.domain.exceptions.NegocioException;
import com.tech.challenge.soat.domain.factory.ProdutoFactory;
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
public class ProdutoFactoryImpl implements ProdutoFactory {

    private final DataProvider dataProvider;

    @Override
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
                .build();
    }
}
