package com.tech.challenge.soat.application.service;

import com.tech.challenge.soat.adapters.factory.ProdutoFactory;
import com.tech.challenge.soat.adapters.mapper.ProdutoMapper;
import com.tech.challenge.soat.adapters.models.in.ProdutoRequest;
import com.tech.challenge.soat.adapters.models.out.ProdutoContentResponse;
import com.tech.challenge.soat.adapters.models.out.ProdutoResponse;
import com.tech.challenge.soat.domain.exceptions.NegocioException;
import com.tech.challenge.soat.domain.models.ProdutoModel;
import com.tech.challenge.soat.domain.repositories.ProdutoRepository;
import com.tech.challenge.soat.domain.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProdutoServiceImpl implements ProdutoService {

    private static final String PRODUTO_NAO_ENCONTRADO = "Produto não encontrado";

    private final ProdutoRepository produtoRepository;

    private final ProdutoMapper produtoMapper;

    private final ProdutoFactory produtoFactory;

    @Override
    public ProdutoResponse salvar(ProdutoRequest produtoRequest) {

        ProdutoModel produto = produtoRepository.save(produtoFactory.novo(produtoRequest));

        return produtoMapper.produtoToProdutoResponse(produto);
    }

    @Override
    public ProdutoContentResponse buscarTodas(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        List<ProdutoModel> list = produtoRepository.findByStatusTrue(pageable);

        List<ProdutoResponse> produtos = produtoMapper.getProdutos(list);

        return ProdutoContentResponse.builder().content(produtos).build();
    }

    @Override
    public ProdutoResponse buscarPorId(UUID uuid) {

        ProdutoModel produto = obterProdutoPorUUID(uuid);

       return produtoMapper.produtoToProdutoResponse(produto);
    }
    @Override
    public ProdutoResponse atualizar(ProdutoRequest produtoRequest) {

        ProdutoModel produtoModel = obterProdutoPorUUID(produtoRequest.getUuid());

        ProdutoModel newProdutoModel = produtoFactory.atualizar(produtoRequest, produtoModel);

        ProdutoModel produto = produtoRepository.save(newProdutoModel);

        return produtoMapper.produtoToProdutoResponse(produto);
    }

    @Override
    public ProdutoResponse delete(UUID produtoId) {

        ProdutoModel produto = obterProdutoPorUUID(produtoId);

        ProdutoModel newProduto = produtoRepository.save(produto);

        return produtoMapper.produtoToProdutoResponse(newProduto);
    }

    public ProdutoModel obterProdutoPorUUID(UUID produtoId) {
        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new NegocioException(PRODUTO_NAO_ENCONTRADO));
    }
}
