package com.tech.challenge.soat.core.applications.ports;

import com.tech.challenge.soat.core.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    Cliente findByCpf(String cpf);
}
