package com.tech.challenge.soat.adapters.driver.v1;


import com.tech.challenge.soat.adapters.driver.v1.model.ClienteModel;
import com.tech.challenge.soat.adapters.driver.v1.model.input.ClienteInput;
import com.tech.challenge.soat.adapters.mapper.ClienteMapper;
import com.tech.challenge.soat.core.applications.service.ClienteService;
import com.tech.challenge.soat.core.domain.Cliente;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("v1/clientes")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ClienteController {

    private final ClienteService clienteService;

    private final ClienteMapper clienteMapper;

    @GetMapping
    public ResponseEntity<Collection<ClienteModel>> todosClientes() {

        List<Cliente> clientes = clienteService.buscarTodos();

        return ResponseEntity.ok(clienteMapper.clientesToClientesModel(clientes));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteModel> buscarPorCpf(@PathVariable("cpf") String cpf) {

        Cliente cliente = clienteService.buscarPorCpf(cpf);

        ClienteModel response = clienteMapper.clienteToClienteModel(cliente);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteModel> salvarCliente(@RequestBody @Valid ClienteInput clienteInput) {


        Cliente clienteSalvo = clienteService.salvar(clienteInput);

        ClienteModel response = clienteMapper.clienteToClienteModel(clienteSalvo);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteModel> atualizar(@PathVariable Long clienteId, @RequestBody ClienteInput clienteInput) {

        Cliente clienteAtual = clienteService.buscarOuFalhar(clienteId);

        clienteMapper.copyToDomainObject(clienteInput, clienteAtual);

        clienteAtual = clienteService.atualizar(clienteAtual);

        ClienteModel response = clienteMapper.clienteToClienteModel(clienteAtual);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{clienteId}")
    public void excluirCliente(@PathVariable Long clienteId) {
        clienteService.excluirCliente(clienteId);
    }
}
