package com.tech.challenge.soat.core.applications.ports;

import com.tech.challenge.soat.core.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    @Query("SELECT c FROM Cliente c WHERE c.situacao = true AND c.cpf = :cpf")
    Cliente findByCpf(String cpf);
}
