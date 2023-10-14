package com.tech.challenge.soat.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusPagamento {
    PAGO("Pago"),
    AGUARDANDO_PAGAMENTO("Aguardando Pagamento");
    private String status;
}
