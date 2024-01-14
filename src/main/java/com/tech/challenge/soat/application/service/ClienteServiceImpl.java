package com.tech.challenge.soat.application.service;

import com.tech.challenge.soat.adapters.factory.ClienteFactory;
import com.tech.challenge.soat.adapters.mapper.ClienteMapper;
import com.tech.challenge.soat.adapters.models.in.ClienteRequest;
import com.tech.challenge.soat.adapters.models.out.ClienteContentResponse;
import com.tech.challenge.soat.adapters.models.out.ClienteResponse;
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
    private final ClienteMapper clienteMapper;
    private final ClienteFactory clienteFactory;

    @Override
    public ClienteContentResponse buscarTodos() {

        List<ClienteModel> list = clienteRepository.findAll();

        List<ClienteResponse> clientes = clienteMapper.getClientes(list);

        return ClienteContentResponse.builder().content(clientes).build();
    }

    @Override
    public ClienteResponse buscarPorCpf(String cpf) {

        ClienteModel cliente = Optional.ofNullable(clienteRepository.findByCpf(cpf))
                .orElseThrow(() -> new NegocioException(I18n.CLIENTE_NAO_ENCONTRADO_PARA_O_CPF + cpf));

        return clienteMapper.clienteToClienteModel(cliente);

    }

    @Override
    public ClienteResponse salvar(ClienteRequest clienteRequest) {

        String cpf = clienteRequest != null ? clienteRequest.getCpf() : null;

        ClienteModel clienteExiste = (cpf != null) ? clienteRepository.findByCpf(cpf) : null;

        if (clienteExiste != null) {
            throw new NegocioException(I18n.CLIENTE_JA_CADASTRADO_PARA_O_CPF + cpf);
        }

        ClienteModel clienteSalvo = clienteRepository.save(clienteFactory.novo(clienteRequest));

        return clienteMapper.clienteToClienteResponse(clienteSalvo);
    }

    @Override
    public ClienteResponse atualizar(UUID clienteId, ClienteRequest clienteRequest) {

        ClienteModel clienteAtual = buscarOuFalhar(clienteId);

        clienteMapper.copyToDomainObject(clienteRequest, clienteAtual);

        clienteAtual = clienteRepository.save(clienteAtual);

        return clienteMapper.clienteToClienteModel(clienteAtual);
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
                    clienteRepository.delete(cliente);
                },
                () -> {
                    throw new ClienteNaoEncontradoException(I18n.CLIENTE_NAO_ENCONTRADO_COM_O_ID + id);
                }
        );
    }

}
