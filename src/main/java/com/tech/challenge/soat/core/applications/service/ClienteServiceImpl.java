package com.tech.challenge.soat.core.applications.service;

import com.tech.challenge.soat.adapters.driver.v1.model.request.ClienteRequest;
import com.tech.challenge.soat.core.applications.factory.ClienteFactory;
import com.tech.challenge.soat.domain.repositories.ClienteRepository;
import com.tech.challenge.soat.domain.models.ClienteModel;
import com.tech.challenge.soat.core.exception.BusinessException;
import com.tech.challenge.soat.core.exception.ClienteNaoEncontradoException;
import com.tech.challenge.soat.domain.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ClienteServiceImpl implements ClienteService {

    private static final String CLIENTE_NAO_ENCONTRADO_COM_O_ID = "Cliente não encontrado com o ID: ";
    private static final String CLIENTE_NAO_ENCONTRADO_PARA_O_CPF = "Cliente não encontrado para o CPF: ";
    private static final String CLIENTE_JA_CADASTRADO_PARA_O_CPF = "Cliente já cadastrado para o CPF ";

    private final ClienteRepository clienteRepository;

    private final ClienteFactory clienteFactory;


    @Override
    public List<ClienteModel> buscarTodos() {

        return clienteRepository.findAll();
    }

    @Override
    public ClienteModel buscarPorCpf(String cpf) {

        return Optional.ofNullable(clienteRepository.findByCpf(cpf))
                .orElseThrow(() -> new BusinessException(CLIENTE_NAO_ENCONTRADO_PARA_O_CPF + cpf));

    }

    @Override
    public ClienteModel salvar(ClienteRequest clienteRequest) {

        String cpf = clienteRequest != null ? clienteRequest.getCpf() : null;

        ClienteModel clienteExiste = (cpf != null) ? clienteRepository.findByCpf(cpf) : null;

        if (clienteExiste != null) {
            throw new BusinessException(CLIENTE_JA_CADASTRADO_PARA_O_CPF + cpf);
        }

        ClienteModel clienteNovo = clienteFactory.novo(clienteRequest);

        return clienteRepository.save(clienteNovo);
    }

    @Override
    public ClienteModel atualizar(ClienteModel cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public ClienteModel buscarOuFalhar(UUID id) {
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
