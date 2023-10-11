package com.tech.challenge.soat.adapters.driver.v1.model.request;

import com.tech.challenge.soat.core.enumerator.StatusPedido;
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
