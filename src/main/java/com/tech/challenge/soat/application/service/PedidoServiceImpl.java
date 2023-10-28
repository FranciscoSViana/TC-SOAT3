package com.tech.challenge.soat.application.service;

import com.tech.challenge.soat.domain.constants.I18n;
import com.tech.challenge.soat.domain.enums.StatusPagamento;
import com.tech.challenge.soat.domain.enums.StatusPedido;
import com.tech.challenge.soat.domain.models.ClienteModel;
import com.tech.challenge.soat.domain.models.PedidoModel;
import com.tech.challenge.soat.domain.models.ProdutoModel;
import com.tech.challenge.soat.domain.ports.out.pagamento.PagamentoPort;
import com.tech.challenge.soat.domain.repositories.ClienteRepository;
import com.tech.challenge.soat.domain.repositories.PedidoRepository;
import com.tech.challenge.soat.domain.repositories.ProdutoRepository;
import com.tech.challenge.soat.domain.services.PedidoService;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final PagamentoPort pagamentoPort;


    @Override
    public PedidoModel salvar(PedidoModel salvarPedido) {
        List<ProdutoModel> produtos = buscarProdutosPorIdOuLancarErro(salvarPedido);
        salvarPedido.setPreco(calcularValorTotalComStreams(produtos));
        ClienteModel cliente = buscarClientePorIdOuLancarErro(salvarPedido);
        validarCriacaoPedido(salvarPedido, cliente);
        pagamentoPort.criarPagamento(salvarPedido);
        return repository.save(salvarPedido);
    }

    @Override
    public PedidoModel pagar(UUID id) {
        PedidoModel pedido = encontrarPedidoPorIdOuLancarErro(id);
        validarPagamentoPedido(pedido);

        pedido.setStatusPedido(StatusPedido.EM_PREPARACAO);
        pedido.setStatusPagamento(StatusPagamento.PAGO);

        return repository.save(pedido);
    }

    @Override
    public PedidoModel alterarStatus(UUID id, StatusPedido status) {
        PedidoModel pedido = encontrarPedidoPorIdOuLancarErro(id);
        validarPedidoParaMudancaDeStatus(pedido, status);

        pedido.setStatusPedido(status);

        return repository.save(pedido);
    }

    @Override
    public PedidoModel buscarPedido(UUID id) {
        return encontrarPedidoPorIdOuLancarErro(id);
    }

    @Override
    public List<PedidoModel> buscarPedidos() {
        return repository.findAll();
    }

    @Override
    public PedidoModel criarPagamento(UUID id) {
        PedidoModel pedido = encontrarPedidoPorIdOuLancarErro(id);
        pedido = pagamentoPort.criarPagamento(pedido);
        return repository.save(pedido);
    }

    private BigDecimal calcularValorTotalComStreams(List<ProdutoModel> produtos) {
        return produtos.stream()
                .filter(produto -> produto != null && produto.getPreco() != null)
                .map(ProdutoModel::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    private PedidoModel buscarPedidoPorIdOuCriarNovoPedido(UUID id) {
        return repository.findById(id).orElse(new PedidoModel());
    }

    private List<ProdutoModel> buscarProdutosPorIdOuLancarErro(PedidoModel pedido) {
        List<UUID> produtoIds = pedido.getProdutos().stream().map(ProdutoModel::getId).toList();
        List<ProdutoModel> produtos = produtoRepository.findAllById(produtoIds);
        if (produtos.isEmpty()) {
            throw new ServiceException(I18n.SEM_PRODUTOS_VALIDOS);
        }
        return produtos;
    }

    private ClienteModel buscarClientePorIdOuLancarErro(PedidoModel pedido) {
        return clienteRepository.findById(pedido.getCliente().getId())
                .orElseThrow(() -> new ServiceException(I18n.CLIENTE_INVALIDO));
    }

    private PedidoModel encontrarPedidoPorIdOuLancarErro(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ServiceException(I18n.PEDIDO_INVALIDO));
    }

    private void validarCriacaoPedido(PedidoModel pedido, ClienteModel cliente) {
        if (!cliente.getId().equals(pedido.getCliente().getId())) {
            throw new ServiceException(I18n.CLIENTE_INVALIDO);
        }
        if (pedido.getStatusPedido() != StatusPedido.RECEBIDO) {
            throw new ServiceException(I18n.NAO_E_POSSIVEL_ADICIONAR_ITENS_AO_PEDIDO);
        }
    }

    private void validarPagamentoPedido(PedidoModel pedido) {
        if (pedido.getStatusPagamento() == StatusPagamento.PAGO) {
            throw new ServiceException(I18n.PEDIDO_JA_ESTA_PAGO);
        }
    }

    private void validarPedidoParaMudancaDeStatus(PedidoModel pedido, StatusPedido novoStatus) {
        switch (novoStatus) {
            case RECEBIDO, EM_PREPARACAO -> throw new ServiceException(I18n.NAO_E_POSSIVEL_ALTERAR_STATUS_DO_PEDIDO);
            case PRONTO -> {
                if (!pedido.getStatusPedido().equals(StatusPedido.RECEBIDO) || !pedido.getStatusPagamento().equals(StatusPagamento.PAGO)) {
                    throw new ServiceException(I18n.NAO_E_POSSIVEL_ALTERAR_STATUS_DO_PEDIDO);
                }
            }
            case FINALIZADO -> {
                if (!pedido.getStatusPedido().equals(StatusPedido.PRONTO) || !pedido.getStatusPagamento().equals(StatusPagamento.PAGO)) {
                    throw new ServiceException(I18n.NAO_E_POSSIVEL_ALTERAR_STATUS_DO_PEDIDO);
                }
            }
        }
    }
}

