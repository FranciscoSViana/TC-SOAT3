package com.tech.challenge.soat.core.applications.service;

import com.tech.challenge.soat.adapters.driver.v1.model.request.ClienteRequest;
import com.tech.challenge.soat.core.domain.Cliente;

import java.util.List;


public interface ClienteService {


    List<Cliente> buscarTodos();

    Cliente buscarPorCpf(String cpf);

    Cliente salvar(ClienteRequest clienteRequest);

    Cliente atualizar(Cliente cliente);

    Cliente buscarOuFalhar(Long clienteId);

    void excluirCliente(Long clienteId);
}
