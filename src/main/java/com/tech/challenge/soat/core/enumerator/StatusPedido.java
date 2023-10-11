package com.tech.challenge.soat.core.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusPedido {
    RECEBIDO("Recebido"),
    EM_PREPARACAO("Em Preparacao"),
    PRONTO("Pronto"),
    FINALIZADO("Finalizado");
    private String status;
}

