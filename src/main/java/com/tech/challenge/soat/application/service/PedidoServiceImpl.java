package com.tech.challenge.soat.application.service;

import com.tech.challenge.soat.adapters.mapper.PedidoMapper;
import com.tech.challenge.soat.adapters.models.in.PedidoRequest;
import com.tech.challenge.soat.adapters.models.out.PedidoResponse;
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
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;

    private final ClienteRepository clienteRepository;

    private final ProdutoRepository produtoRepository;

    private final PagamentoPort pagamentoPort;

    private final PedidoMapper pedidoMapper;


    @Override
    public PedidoResponse salvar(PedidoRequest request) {

        PedidoModel salvarPedido = pedidoMapper.pedidoRequestToPedidoModel(request);

        List<ProdutoModel> produtos = buscarProdutosPorIdOuLancarErro(salvarPedido);

        salvarPedido.setPreco(calcularValorTotalComStreams(produtos));

        ClienteModel cliente = buscarClientePorIdOuLancarErro(salvarPedido);

        validarCriacaoPedido(salvarPedido, cliente);

        repository.save(salvarPedido);

        return pedidoMapper.pedidoToPedidoRespose(salvarPedido);
    }


    @Override
    public PedidoResponse pagar(UUID id) {

        PedidoModel pedido = encontrarPedidoPorIdOuLancarErro(id);

        pedido.setStatusPagamento(StatusPagamento.PAGO);

        repository.save(pedido);

        return pedidoMapper.pedidoToPedidoRespose(pedido);
    }

    @Override
    public PedidoResponse alterarStatus(UUID id, StatusPedido status) {

        PedidoModel pedido = encontrarPedidoPorIdOuLancarErro(id);

        if (pedido.getStatusPedido() != null) {

            pedido.setStatusPedido(status);

        }

        repository.save(pedido);

        return pedidoMapper.pedidoToPedidoRespose(pedido);
    }

    @Override
    public PedidoResponse buscarPedido(UUID id) {

        PedidoModel pedido = encontrarPedidoPorIdOuLancarErro(id);

        return pedidoMapper.pedidoToPedidoRespose(pedido);
    }

    @Override
    public List<PedidoResponse> buscarPedidos() {

        List<PedidoModel> pedidos = repository.findAll();

        return pedidoMapper.pedidosToPedidosRespose(pedidos);
    }

    @Override
    public PedidoResponse criarPagamento(UUID id) {

        PedidoModel pedido = encontrarPedidoPorIdOuLancarErro(id);

        pedido = pagamentoPort.criarPagamento(pedido);

        return pedidoMapper.pedidoToPedidoRespose(pedido);
    }

    @Override
    public Optional<PedidoModel> obterPorUUID(String idPagamento) {
        return repository.findById(UUID.fromString(idPagamento));
    }

    @Override
    public PedidoModel obterPorIdPagamentoMP(String idPagamento) {
        return repository.findByIdPagamentoMP(idPagamento);
    }

    @Override
    public PedidoModel confirmarPagamento(PedidoModel pedido) {


        pedido.setStatusPagamento(StatusPagamento.PAGO);

        return repository.save(pedido);

    }

    @Override
    public PedidoModel salvar(PedidoModel pedido) {
        return repository.save(pedido);
    }


    private BigDecimal calcularValorTotalComStreams(List<ProdutoModel> produtos) {
        return produtos.stream()
                .filter(produto -> produto != null && produto.getPreco() != null)
                .map(ProdutoModel::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
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


}

