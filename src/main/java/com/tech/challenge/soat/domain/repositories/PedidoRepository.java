package com.tech.challenge.soat.domain.repositories;


import com.tech.challenge.soat.domain.enums.StatusPagamento;
import com.tech.challenge.soat.domain.models.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, UUID> {
    PedidoModel findByIdPagamentoMP(String idPagamento);

    @Query("SELECT p FROM PedidoModel p WHERE p.idPagamentoMP IS NOT NULL AND p.statusPagamento = :statusPagamento")
    List<PedidoModel> findPedidosComPagamentoAguardando(@Param("statusPagamento") StatusPagamento statusPagamento);

}
