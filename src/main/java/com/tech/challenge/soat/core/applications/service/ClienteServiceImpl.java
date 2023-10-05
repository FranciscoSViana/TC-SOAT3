package com.tech.challenge.soat.core.applications.service;

import com.tech.challenge.soat.adapters.driver.v1.model.request.ClienteRequest;
import com.tech.challenge.soat.core.applications.factory.ClienteFactory;
import com.tech.challenge.soat.core.applications.ports.ClienteRepository;
import com.tech.challenge.soat.core.domain.Cliente;
import com.tech.challenge.soat.core.exception.ClienteNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ClienteServiceImpl implements ClienteService{

    public static final String CLIENTE_NAO_ENCONTRADO_COM_O_ID = "Cliente não encontrado com o ID: ";

    private final ClienteRepository clienteRepository;

    private final ClienteFactory clienteFactory;

    @Override
    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    @Override
    public Cliente salvar(ClienteRequest clienteRequest) {

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
