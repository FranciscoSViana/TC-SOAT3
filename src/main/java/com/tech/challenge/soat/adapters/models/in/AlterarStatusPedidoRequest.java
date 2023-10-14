package com.tech.challenge.soat.adapters.models.in;

import com.tech.challenge.soat.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlterarStatusPedidoRequest {
    StatusPedido status;
}
