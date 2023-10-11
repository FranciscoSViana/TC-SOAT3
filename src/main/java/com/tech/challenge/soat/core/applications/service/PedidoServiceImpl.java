package com.tech.challenge.soat.core.applications.service;

import com.tech.challenge.soat.adapters.driver.v1.model.request.PedidoRequest;
import com.tech.challenge.soat.core.applications.ports.ClienteRepository;
import com.tech.challenge.soat.core.applications.ports.PedidoRepository;
import com.tech.challenge.soat.core.applications.ports.ProdutoRepository;
import com.tech.challenge.soat.core.domain.Cliente;
import com.tech.challenge.soat.core.domain.Pedido;
import com.tech.challenge.soat.core.domain.Produto;
import com.tech.challenge.soat.core.enumerator.StatusPagamento;
import com.tech.challenge.soat.core.enumerator.StatusPedido;
import com.tech.challenge.soat.domain.constants.MensagensError;
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
    public Pedido salvar(PedidoRequest request) {
        Pedido pedido = buscarPedidoPorIdOuCriarNovoPedido(request.getId());
        List<Produto> produtos = buscarProdutosPorIdOuLancarErro(request.getProdutos());
        Cliente cliente = buscarClientePorIdOuLancarErro(request.getCliente());

        validarCriacaoPedido(pedido, cliente);
        produtos.forEach(pedido::adicionarProdutoAoPedido);

        return repository.save(pedido);
    }

    @Override
    public Pedido pagar(UUID id) {
        Pedido pedido = encontrarPedidoPorIdOuLancarErro(id);
        validarPagamentoPedido(pedido);

        pedido.setStatusPedido(StatusPedido.EM_PREPARACAO);
        pedido.setStatusPagamento(StatusPagamento.PAGO);

        return repository.save(pedido);
    }

    @Override
    public Pedido alterarStatus(UUID id, StatusPedido status) {
        Pedido pedido = encontrarPedidoPorIdOuLancarErro(id);
        validarPedidoParaMudancaDeStatus(pedido, status);

        pedido.setStatusPedido(status);

        return repository.save(pedido);
    }

    @Override
    public Pedido buscarPedido(UUID id) {
        return encontrarPedidoPorIdOuLancarErro(id);
    }

    @Override
    public List<Pedido> buscarPedidos() {
        return repository.findAll();
    }

    private Pedido buscarPedidoPorIdOuCriarNovoPedido(UUID id) {
        return repository.findById(id).orElse(new Pedido());
    }

    private List<Produto> buscarProdutosPorIdOuLancarErro(List<UUID> ids) {
        List<Produto> produtos = produtoRepository.findAllById(ids);
        if (produtos.isEmpty()) {
            throw new ServiceException(MensagensError.SEM_PRODUTOS_VALIDOS);
        }
        return produtos;
    }

    private Cliente buscarClientePorIdOuLancarErro(UUID clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ServiceException(MensagensError.CLIENTE_INVALIDO));
    }

    private Pedido encontrarPedidoPorIdOuLancarErro(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ServiceException(MensagensError.PEDIDO_INVALIDO));
    }

    private void validarCriacaoPedido(Pedido pedido, Cliente cliente) {
        if (!cliente.getId().equals(pedido.getCliente().getId())) {
            throw new ServiceException(MensagensError.CLIENTE_INVALIDO);
        }
        if (pedido.getStatusPedido() != StatusPedido.RECEBIDO || pedido.getStatusPagamento() != StatusPagamento.AGUARDANDO_PAGAMENTO) {
            throw new ServiceException(MensagensError.NAO_E_POSSIVEL_ADICIONAR_ITENS_AO_PEDIDO);
        }
    }

    private void validarPagamentoPedido(Pedido pedido) {
        if (pedido.getStatusPagamento() == StatusPagamento.PAGO) {
            throw new ServiceException(MensagensError.PEDIDO_JA_ESTA_PAGO);
        }
    }

    private void validarPedidoParaMudancaDeStatus(Pedido pedido, StatusPedido novoStatus) {
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

