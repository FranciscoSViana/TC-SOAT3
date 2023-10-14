package com.tech.challenge.soat.adapters.mapper;



import com.tech.challenge.soat.adapters.driver.v1.model.response.ClienteResponse;
import com.tech.challenge.soat.adapters.driver.v1.model.request.ClienteRequest;
import com.tech.challenge.soat.domain.models.ClienteModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
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

    public Collection<ClienteResponse> clientesToClientesModel(Collection<ClienteModel> clientes) {
        return clientes.stream()
                .map(cli -> modelMapper.map(cli, ClienteResponse.class))
                .collect(Collectors.toList());
    }

    public void copyToDomainObject(ClienteRequest clienteRequest, ClienteModel cliente) {
        modelMapper.map(clienteRequest, cliente);
    }
}
