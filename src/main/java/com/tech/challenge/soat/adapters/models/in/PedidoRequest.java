package com.tech.challenge.soat.adapters.models.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequest {
    private UUID id;
    private UUID cliente;
    private List<UUID> produtos;
    private LocalTime tempoPreparo;
}
