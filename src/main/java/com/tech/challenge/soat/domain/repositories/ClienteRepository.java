package com.tech.challenge.soat.domain.repositories;

import com.tech.challenge.soat.domain.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


public interface ClienteRepository {

    ClienteModel findByCpf(String cpf);

    ClienteModel save(ClienteModel cliente);

    Optional<Object> findById(UUID id);
}
