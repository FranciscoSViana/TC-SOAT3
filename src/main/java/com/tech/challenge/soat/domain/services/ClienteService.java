package com.tech.challenge.soat.domain.services;

import com.tech.challenge.soat.adapters.models.in.ClienteRequest;
import com.tech.challenge.soat.adapters.models.out.ClienteContentResponse;
import com.tech.challenge.soat.adapters.models.out.ClienteResponse;
import com.tech.challenge.soat.domain.models.ClienteModel;

import java.util.List;
import java.util.UUID;


public interface ClienteService {


    ClienteContentResponse buscarTodos();

    ClienteResponse buscarPorCpf(String cpf);

    ClienteResponse salvar(ClienteRequest clienteRequest);

    ClienteResponse atualizar(UUID clienteId, ClienteRequest clienteRequest);

    ClienteModel buscarOuFalhar(UUID clienteId);

    void excluirCliente(UUID clienteId);
}
