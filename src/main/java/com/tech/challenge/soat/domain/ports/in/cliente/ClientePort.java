package com.tech.challenge.soat.domain.ports.in.cliente;

import com.tech.challenge.soat.adapters.models.in.ClienteRequest;
import com.tech.challenge.soat.adapters.models.out.ClienteResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.UUID;

public interface ClientePort {

    ResponseEntity<Collection<ClienteResponse>> todosClientes();


    ResponseEntity<ClienteResponse> buscarPorCpf(@PathVariable("cpf") String cpf);


    ResponseEntity<ClienteResponse> salvarCliente(@RequestBody @Valid ClienteRequest clienteRequest);


    ResponseEntity<ClienteResponse> atualizar(@PathVariable UUID clienteId, @RequestBody ClienteRequest clienteRequest);


    void excluirCliente(@PathVariable UUID clienteId);
}
