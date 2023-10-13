package com.tech.challenge.soat.core.applications.service;

import com.tech.challenge.soat.adapters.driver.v1.model.request.ClienteRequest;
import com.tech.challenge.soat.core.applications.factory.ClienteFactory;
import com.tech.challenge.soat.core.applications.feign.client.request.PagamentoRequest;
import com.tech.challenge.soat.core.applications.feign.client.response.PagamentoResponse;
import com.tech.challenge.soat.core.applications.feign.service.MercadoPagoService;
import com.tech.challenge.soat.core.applications.ports.ClienteRepository;
import com.tech.challenge.soat.core.domain.Cliente;
import com.tech.challenge.soat.core.exception.BusinessException;
import com.tech.challenge.soat.core.exception.ClienteNaoEncontradoException;
import com.tech.challenge.soat.core.exception.NegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;


@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ClienteServiceImpl implements ClienteService{

    private static final String CLIENTE_NAO_ENCONTRADO_COM_O_ID = "Cliente não encontrado com o ID: ";
    private static final String CLIENTE_NAO_ENCONTRADO_PARA_O_CPF = "Cliente não encontrado para o CPF: ";
    private static final String CLIENTE_JA_CADASTRADO_PARA_O_CPF = "Cliente já cadastrado para o CPF ";

    private final ClienteRepository clienteRepository;

    private final ClienteFactory clienteFactory;

    private final MercadoPagoService mercadoPagoService;

    @Override
    public List<Cliente> buscarTodos() {

        PagamentoResponse res =  mercadoPagoService.criarPagamento(PagamentoRequest.builder().build());


        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorCpf(String cpf) {

        return Optional.ofNullable(clienteRepository.findByCpf(cpf))
                .orElseThrow(() -> new BusinessException(CLIENTE_NAO_ENCONTRADO_PARA_O_CPF + cpf));

    }

    @Override
    public Cliente salvar(ClienteRequest clienteRequest) {

        String cpf = clienteRequest != null ? clienteRequest.getCpf() : null;

        Cliente clienteExiste = (cpf != null) ? clienteRepository.findByCpf(cpf) : null;

        if (clienteExiste != null) {
            throw new BusinessException(CLIENTE_JA_CADASTRADO_PARA_O_CPF + cpf);
        }

        Cliente clienteNovo = clienteFactory.novo(clienteRequest);

        return clienteRepository.save(clienteNovo);
    }

    @Override
    public Cliente atualizar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente buscarOuFalhar(UUID id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(String.valueOf(id)));
    }

    @Override
    public void excluirCliente(UUID id) {

        clienteRepository.findById(id).ifPresentOrElse(
                cliente -> {
                    cliente.setSituacao(Boolean.FALSE);
                    clienteRepository.save(cliente);
                },
                () -> {
                    throw new ClienteNaoEncontradoException(CLIENTE_NAO_ENCONTRADO_COM_O_ID + id);
                }
        );
    }

}
