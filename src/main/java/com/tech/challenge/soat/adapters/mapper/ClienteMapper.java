package com.tech.challenge.soat.adapters.mapper;



import com.tech.challenge.soat.adapters.driver.v1.model.response.ClienteResponse;
import com.tech.challenge.soat.adapters.driver.v1.model.request.ClienteRequest;
import com.tech.challenge.soat.core.domain.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class ClienteMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Cliente clienteInputToCliente(ClienteRequest clienteRequest) {
        return modelMapper.map(clienteRequest, Cliente.class);
    }

    public ClienteResponse clienteToClienteModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteResponse.class);
    }

    public Collection<ClienteResponse> clientesToClientesModel(Collection<Cliente> clientes) {
        return clientes.stream()
                .map(cli -> modelMapper.map(cli, ClienteResponse.class))
                .collect(Collectors.toList());
    }

    public void copyToDomainObject(ClienteRequest clienteRequest, Cliente cliente) {
        modelMapper.map(clienteRequest, cliente);
    }
}
