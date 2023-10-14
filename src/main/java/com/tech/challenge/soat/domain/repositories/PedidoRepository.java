package com.tech.challenge.soat.domain.repositories;


import com.tech.challenge.soat.domain.models.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface PedidoRepository  extends JpaRepository<PedidoModel, UUID> {
}
