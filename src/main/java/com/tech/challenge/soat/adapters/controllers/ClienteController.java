package com.tech.challenge.soat.adapters.controllers;


import com.tech.challenge.soat.adapters.models.in.ClienteRequest;
import com.tech.challenge.soat.adapters.models.out.ClienteContentResponse;
import com.tech.challenge.soat.adapters.models.out.ClienteResponse;
import com.tech.challenge.soat.domain.services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/clientes")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<ClienteContentResponse> todosClientes() {

        ClienteContentResponse clienteContentResponse = clienteService.buscarTodos();

        return new ResponseEntity<>(clienteContentResponse, HttpStatus.OK);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteResponse> buscarPorCpf(@PathVariable("cpf") String cpf) {

        ClienteResponse response = clienteService.buscarPorCpf(cpf);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteResponse> salvarCliente(@RequestBody @Valid ClienteRequest clienteRequest) {

        ClienteResponse clienteSalvo = clienteService.salvar(clienteRequest);

        return ResponseEntity.ok(clienteSalvo);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteResponse> atualizar(@PathVariable UUID clienteId, @RequestBody ClienteRequest clienteRequest) {

        ClienteResponse response = clienteService.atualizar(clienteId, clienteRequest);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{clienteId}")
    public void excluirCliente(@PathVariable UUID clienteId) {
        clienteService.excluirCliente(clienteId);
    }
}
