package com.tech.challenge.soat.adapters.mapper;


import com.tech.challenge.soat.adapters.models.in.ClienteRequest;
import com.tech.challenge.soat.adapters.models.out.ClienteResponse;
import com.tech.challenge.soat.domain.models.ClienteModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ClienteMapper {

    private final ModelMapper modelMapper;

    public ClienteModel clienteInputToCliente(ClienteRequest clienteRequest) {
        return modelMapper.map(clienteRequest, ClienteModel.class);
    }

    public ClienteResponse clienteToClienteModel(ClienteModel cliente) {
        return modelMapper.map(cliente, ClienteResponse.class);
    }

    public ClienteResponse clienteToClienteResponse(ClienteModel clienteModel) {
        return ClienteResponse.builder()
                .uuid(clienteModel.getId())
                .nome(clienteModel.getNome())
                .cpf(clienteModel.getCpf())
                .email(clienteModel.getEmail())
                .telefone(clienteModel.getTelefone())
                .build();
    }

    public List<ClienteResponse> getClientes(List<ClienteModel> list) {
        List<ClienteResponse> clientes = new ArrayList<>();
        list.forEach(client -> {
            ClienteResponse clienteResponse = clienteToClienteResponse(client);
            clientes.add(clienteResponse);
        });

        return clientes;
    }

    public Collection<ClienteResponse> clientesToClientesModel(Collection<ClienteModel> clientes) {
        return clientes.stream()
                .map(cli -> modelMapper.map(cli, ClienteResponse.class))
                .collect(Collectors.toList());
    }

    public void copyToDomainObject(ClienteRequest clienteRequest, ClienteModel cliente) {
        modelMapper.map(clienteRequest, cliente);
    }
}
