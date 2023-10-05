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

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ClienteServiceImpl implements ClienteService{

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
    public Cliente buscarOuFalhar(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));
    }

    @Override
    public void excluirCliente(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }

}
