package com.tech.challenge.soat.domain.services;

import com.tech.challenge.soat.domain.models.ClienteModel;

import java.util.List;
import java.util.UUID;


public interface ClienteService {


    List<ClienteModel> buscarTodos();

    ClienteModel buscarPorCpf(String cpf);

    ClienteModel salvar(ClienteModel cliente);

    ClienteModel atualizar(ClienteModel cliente);

    ClienteModel buscarOuFalhar(UUID clienteId);

    void excluirCliente(UUID clienteId);
}
