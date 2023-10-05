package com.tech.challenge.soat.adapters.driver.v1;


import com.tech.challenge.soat.adapters.driver.v1.model.response.ClienteResponse;
import com.tech.challenge.soat.adapters.driver.v1.model.request.ClienteRequest;
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
import java.util.UUID;

@RestController
@RequestMapping("v1/clientes")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ClienteController {

    private final ClienteService clienteService;

    private final ClienteMapper clienteMapper;

    @GetMapping
    public ResponseEntity<Collection<ClienteResponse>> todosClientes() {

        List<Cliente> clientes = clienteService.buscarTodos();

        return ResponseEntity.ok(clienteMapper.clientesToClientesModel(clientes));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteResponse> buscarPorCpf(@PathVariable("cpf") String cpf) {

        Cliente cliente = clienteService.buscarPorCpf(cpf);

        ClienteResponse response = clienteMapper.clienteToClienteModel(cliente);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteResponse> salvarCliente(@RequestBody @Valid ClienteRequest clienteRequest) {


        Cliente clienteSalvo = clienteService.salvar(clienteRequest);

        ClienteResponse response = clienteMapper.clienteToClienteModel(clienteSalvo);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteResponse> atualizar(@PathVariable UUID clienteId, @RequestBody ClienteRequest clienteRequest) {

        Cliente clienteAtual = clienteService.buscarOuFalhar(clienteId);

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
