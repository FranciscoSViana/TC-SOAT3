package com.tech.challenge.soat.infra.persistence.jpa.repositories;

import com.tech.challenge.soat.infra.persistence.jpa.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaClienteRepository extends JpaRepository<ClienteEntity, UUID> {
    @Query("SELECT c FROM ClienteEntity c WHERE c.situacao = true AND c.cpf = :cpf")
    ClienteEntity findByCpf(String cpf);
}
