package com.tech.challenge.soat.adapters.models.out;

import com.tech.challenge.soat.domain.enums.StatusPagamento;
import com.tech.challenge.soat.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    private BigDecimal preco;
    private StatusPagamento statusPagamento;
    private LocalTime tempoPreparo;
    private String codigoPix;
    private String qrCode;
}
