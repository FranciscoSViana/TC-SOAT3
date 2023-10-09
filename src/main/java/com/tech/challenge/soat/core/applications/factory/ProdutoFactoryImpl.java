package com.tech.challenge.soat.core.applications.factory;

import com.tech.challenge.soat.adapters.driver.v1.model.request.ProdutoRequest;
import com.tech.challenge.soat.core.domain.Produto;
import com.tech.challenge.soat.core.enumerator.TipoCategoria;
import com.tech.challenge.soat.core.exception.NegocioException;
import com.tech.challenge.soat.shared.util.ImagemUtil;
import com.tech.challenge.soat.shared.util.provider.DataProvider;
import com.tech.challenge.soat.shared.util.provider.UUIDProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProdutoFactoryImpl implements ProdutoFactory {


    private final UUIDProvider uuidProvider;
    private final DataProvider dataProvider;
    private final ImagemUtil imagemUtil;

    @Override
    public Produto novo(ProdutoRequest produtoRequest) {

        if (Objects.isNull(produtoRequest)) {
            throw new NegocioException("Produto não pode ser nulo");
        }

        return Produto.
                builder()
                .id(uuidProvider.geradorUUID())
                .nome(produtoRequest.getNome())
                .preco(produtoRequest.getPreco())
                .dataHoraCriacao(dataProvider.obterDataHoraAtual())
                .descricao(produtoRequest.getDescricao())
                .imagem(imagemUtil.decodeBase64(produtoRequest.getImagemBase64()))
                .categoria(TipoCategoria.fromName(produtoRequest.getCategoria()))
                .build();
    }
}
