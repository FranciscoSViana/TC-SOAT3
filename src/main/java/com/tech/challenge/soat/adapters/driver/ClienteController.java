package com.tech.challenge.soat.adapters.driver;

import com.tech.challenge.soat.adapters.driver.model.ClienteModel;
import com.tech.challenge.soat.adapters.driver.model.input.ClienteInput;
import com.tech.challenge.soat.adapters.mapper.ClienteMapper;
import com.tech.challenge.soat.core.applications.service.ClienteService;
import com.tech.challenge.soat.core.domain.Cliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<ClienteModel> todosClientes() {
        List<Cliente> todos = clienteService.todosClientes();

        return clienteMapper.toCollectionModel(todos);
    }

    @GetMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteModel buscarPorCpf(@PathVariable("cpf") String cpf) {
        Cliente cliente = clienteService.buscarPorCpf(cpf);

        return clienteMapper.toModel(cliente);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteModel salvarCliente(@RequestBody @Valid ClienteInput clienteInput) {
        Cliente cliente = clienteMapper.toDomainObject(clienteInput);
        cliente = clienteService.salvarCliente(cliente);

        return clienteMapper.toModel(cliente);
    }

    @PutMapping("/{clienteId}")
    public ClienteModel atualizar(@PathVariable Long clienteId, @RequestBody ClienteInput clienteInput) {
        Cliente clienteAtual = clienteService.buscarOuFalhar(clienteId);
        clienteMapper.copyToDomainObject(clienteInput, clienteAtual);
        clienteAtual = clienteService.salvarCliente(clienteAtual);

        return clienteMapper.toModel(clienteAtual);
    }

    @DeleteMapping("/{clienteId}")
    public void excluirCliente(@PathVariable Long clienteId) {
        clienteService.excluirCliente(clienteId);
    }
}
