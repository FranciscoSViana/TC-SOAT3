package com.tech.challenge.soat.adapters.driver.v1.model.response;

import com.tech.challenge.soat.core.domain.Cliente;
import com.tech.challenge.soat.core.domain.Produto;
import com.tech.challenge.soat.core.enumerator.StatusPagamento;
import com.tech.challenge.soat.core.enumerator.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.rmi.server.UID;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponse {
    private UUID id;
    private List<UUID> produtos;
    private UUID cliente;
    private StatusPedido statusPedido;
    private Double preco;
    private StatusPagamento statusPagamento;
    private LocalTime tempoPreparo;
}
