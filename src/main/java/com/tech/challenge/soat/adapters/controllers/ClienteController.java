package com.tech.challenge.soat.adapters.controllers;


import com.tech.challenge.soat.adapters.factory.ClienteFactory;
import com.tech.challenge.soat.adapters.mapper.ClienteMapper;
import com.tech.challenge.soat.adapters.models.in.ClienteRequest;
import com.tech.challenge.soat.adapters.models.out.ClienteResponse;
import com.tech.challenge.soat.domain.models.ClienteModel;
import com.tech.challenge.soat.domain.services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/clientes")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ClienteController   {

    private final ClienteService clienteService;

    private final ClienteMapper clienteMapper;

    private final ClienteFactory clienteFactory;

    @GetMapping
    public ResponseEntity<Collection<ClienteResponse>> todosClientes() {

        List<ClienteModel> clientes = clienteService.buscarTodos();

        return ResponseEntity.ok(clienteMapper.clientesToClientesModel(clientes));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteResponse> buscarPorCpf(@PathVariable("cpf") String cpf) {

        ClienteModel cliente = clienteService.buscarPorCpf(cpf);

        ClienteResponse response = clienteMapper.clienteToClienteModel(cliente);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteResponse> salvarCliente(@RequestBody @Valid ClienteRequest clienteRequest) {


        ClienteModel clienteSalvo = clienteService.salvar(clienteFactory.novo(clienteRequest));

        ClienteResponse response = clienteMapper.clienteToClienteModel(clienteSalvo);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteResponse> atualizar(@PathVariable UUID clienteId, @RequestBody ClienteRequest clienteRequest) {

        ClienteModel clienteAtual = clienteService.buscarOuFalhar(clienteId);

        clienteMapper.copyToDomainObject(clienteRequest, clienteAtual);

        clienteAtual = clienteService.atualizar(clienteAtual);

        ClienteResponse response = clienteMapper.clienteToClienteModel(clienteAtual);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{clienteId}")
    public void excluirCliente(@PathVariable UUID clienteId) {
        clienteService.excluirCliente(clienteId);
    }
}
