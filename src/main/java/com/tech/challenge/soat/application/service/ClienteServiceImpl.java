package com.tech.challenge.soat.application.service;

import com.tech.challenge.soat.domain.constants.I18n;
import com.tech.challenge.soat.domain.exceptions.ClienteNaoEncontradoException;
import com.tech.challenge.soat.domain.exceptions.NegocioException;
import com.tech.challenge.soat.domain.models.ClienteModel;
import com.tech.challenge.soat.domain.repositories.ClienteRepository;
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

    private final ClienteRepository clienteRepository;

    @Override
    public List<ClienteModel> buscarTodos() {

        return clienteRepository.findAll();
    }

    @Override
    public ClienteModel buscarPorCpf(String cpf) {

        return Optional.ofNullable(clienteRepository.findByCpf(cpf))
                .orElseThrow(() -> new NegocioException(I18n.CLIENTE_NAO_ENCONTRADO_PARA_O_CPF + cpf));

    }

    @Override
    public ClienteModel salvar(ClienteModel cliente) {

        String cpf = cliente != null ? cliente.getCpf() : null;

        ClienteModel clienteExiste = (cpf != null) ? clienteRepository.findByCpf(cpf) : null;

        if (clienteExiste != null) {
            throw new NegocioException(I18n.CLIENTE_JA_CADASTRADO_PARA_O_CPF + cpf);
        }

        return clienteRepository.save(cliente);
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
                    throw new ClienteNaoEncontradoException(I18n.CLIENTE_NAO_ENCONTRADO_COM_O_ID + id);
                }
        );
    }

}
