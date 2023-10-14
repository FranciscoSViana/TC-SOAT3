package com.tech.challenge.soat.infra.persistence.jpa.repositories;


import com.tech.challenge.soat.domain.models.PedidoModel;
import com.tech.challenge.soat.infra.persistence.jpa.entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaPedidoRepository extends JpaRepository<PedidoEntity, UUID> {
}
