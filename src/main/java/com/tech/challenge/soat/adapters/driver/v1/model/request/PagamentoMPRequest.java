package com.tech.challenge.soat.adapters.driver.v1.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoMPRequest {

    private BigDecimal valor;
    private String email;

}
