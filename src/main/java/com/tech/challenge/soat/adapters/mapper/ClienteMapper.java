package com.tech.challenge.soat.adapters.mapper;



import com.tech.challenge.soat.adapters.driver.v1.model.ClienteModel;
import com.tech.challenge.soat.adapters.driver.v1.model.input.ClienteInput;
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

    public Cliente clienteInputToCliente(ClienteInput clienteInput) {
        return modelMapper.map(clienteInput, Cliente.class);
    }

    public ClienteModel clienteToClienteModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteModel.class);
    }

    public Collection<ClienteModel> clientesToClientesModel(Collection<Cliente> clientes) {
        return clientes.stream()
                .map(cli -> modelMapper.map(cli, ClienteModel.class))
                .collect(Collectors.toList());
    }

    public void copyToDomainObject(ClienteInput clienteInput, Cliente cliente) {
        modelMapper.map(clienteInput, cliente);
    }
}
