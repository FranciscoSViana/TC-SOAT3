package com.tech.challenge.soat.core.applications.service;

import com.tech.challenge.soat.core.applications.ports.ClienteRepository;
import com.tech.challenge.soat.core.domain.Cliente;
import com.tech.challenge.soat.core.domain.exception.ClienteNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> todosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente buscarOuFalhar(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));
    }

    public void excluirCliente(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
