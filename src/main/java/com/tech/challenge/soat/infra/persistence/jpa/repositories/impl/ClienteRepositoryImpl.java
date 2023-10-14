package com.tech.challenge.soat.infra.persistence.jpa.repositories.impl;

import com.tech.challenge.soat.domain.models.ClienteModel;
import com.tech.challenge.soat.domain.repositories.ClienteRepository;
import com.tech.challenge.soat.infra.mappers.impl.ClienteModelToEntityMapperImpl;
import com.tech.challenge.soat.infra.persistence.jpa.repositories.JpaClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ClienteRepositoryImpl implements ClienteRepository {

    private final JpaClienteRepository clienteRepository;
    private final ClienteModelToEntityMapperImpl mapper;
    @Override
    public ClienteModel findByCpf(String cpf) {
        return mapper.toModel(clienteRepository.findByCpf(cpf));
    }

    @Override
    public ClienteModel save(ClienteModel cliente) {
        return mapper.toModel(clienteRepository.save(mapper.toEntity(cliente)));
    }

    @Override
    public Optional<ClienteModel> findById(UUID id) {
        return Optional.empty();
    }
}
