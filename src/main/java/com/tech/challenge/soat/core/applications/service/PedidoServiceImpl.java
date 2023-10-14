package com.tech.challenge.soat.core.applications.service;

import com.tech.challenge.soat.adapters.driver.v1.model.request.PedidoRequest;
import com.tech.challenge.soat.domain.repositories.ClienteRepository;
import com.tech.challenge.soat.domain.repositories.PedidoRepository;
import com.tech.challenge.soat.domain.repositories.ProdutoRepository;
import com.tech.challenge.soat.domain.models.ClienteModel;
import com.tech.challenge.soat.domain.models.PedidoModel;
import com.tech.challenge.soat.domain.models.ProdutoModel;
import com.tech.challenge.soat.core.enumerator.StatusPagamento;
import com.tech.challenge.soat.core.enumerator.StatusPedido;
import com.tech.challenge.soat.domain.constants.MensagensError;
import com.tech.challenge.soat.domain.services.PedidoService;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    @Override
    public PedidoModel salvar(PedidoRequest request) {
        PedidoModel pedido = buscarPedidoPorIdOuCriarNovoPedido(request.getId());
        List<ProdutoModel> produtos = buscarProdutosPorIdOuLancarErro(request.getProdutos());
        ClienteModel cliente = buscarClientePorIdOuLancarErro(request.getCliente());

        validarCriacaoPedido(pedido, cliente);
        produtos.forEach(pedido::adicionarProdutoAoPedido);

        return repository.save(pedido);
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

    private PedidoModel buscarPedidoPorIdOuCriarNovoPedido(UUID id) {
        return repository.findById(id).orElse(new PedidoModel());
    }

    private List<ProdutoModel> buscarProdutosPorIdOuLancarErro(List<UUID> ids) {
        List<ProdutoModel> produtos = produtoRepository.findAllById(ids);
        if (produtos.isEmpty()) {
            throw new ServiceException(MensagensError.SEM_PRODUTOS_VALIDOS);
        }
        return produtos;
    }

    private ClienteModel buscarClientePorIdOuLancarErro(UUID clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ServiceException(MensagensError.CLIENTE_INVALIDO));
    }

    private PedidoModel encontrarPedidoPorIdOuLancarErro(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ServiceException(MensagensError.PEDIDO_INVALIDO));
    }

    private void validarCriacaoPedido(PedidoModel pedido, ClienteModel cliente) {
        if (!cliente.getId().equals(pedido.getCliente().getId())) {
            throw new ServiceException(MensagensError.CLIENTE_INVALIDO);
        }
        if (pedido.getStatusPedido() != StatusPedido.RECEBIDO || pedido.getStatusPagamento() != StatusPagamento.AGUARDANDO_PAGAMENTO) {
            throw new ServiceException(MensagensError.NAO_E_POSSIVEL_ADICIONAR_ITENS_AO_PEDIDO);
        }
    }

    private void validarPagamentoPedido(PedidoModel pedido) {
        if (pedido.getStatusPagamento() == StatusPagamento.PAGO) {
            throw new ServiceException(MensagensError.PEDIDO_JA_ESTA_PAGO);
        }
    }

    private void validarPedidoParaMudancaDeStatus(PedidoModel pedido, StatusPedido novoStatus) {
        switch (novoStatus) {
            case RECEBIDO, EM_PREPARACAO -> throw new ServiceException(MensagensError.NAO_E_POSSIVEL_ALTERAR_STATUS_DO_PEDIDO);
            case PRONTO -> {
                if (!pedido.getStatusPedido().equals(StatusPedido.RECEBIDO) || !pedido.getStatusPagamento().equals(StatusPagamento.PAGO)) {
                    throw new ServiceException(MensagensError.NAO_E_POSSIVEL_ALTERAR_STATUS_DO_PEDIDO);
                }
            }
            case FINALIZADO -> {
                if (!pedido.getStatusPedido().equals(StatusPedido.PRONTO) || !pedido.getStatusPagamento().equals(StatusPagamento.PAGO)) {
                    throw new ServiceException(MensagensError.NAO_E_POSSIVEL_ALTERAR_STATUS_DO_PEDIDO);
                }
            }
        }
    }
}

