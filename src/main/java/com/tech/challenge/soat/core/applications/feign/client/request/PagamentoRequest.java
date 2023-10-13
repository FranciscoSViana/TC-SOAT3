package com.tech.challenge.soat.core.applications.feign.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagamentoRequest {

    @JsonProperty("transaction_amount")
    private BigDecimal valor;

    @JsonProperty("description")
    private String descricao;

    @JsonProperty("payment_method_id")
    private String metodoDePagamento;

    private Pagador payer;


}
