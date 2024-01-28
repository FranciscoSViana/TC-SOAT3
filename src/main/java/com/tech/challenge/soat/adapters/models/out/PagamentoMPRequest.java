package com.tech.challenge.soat.adapters.models.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagamentoMPRequest {

    @JsonProperty("transaction_amount")
    private BigDecimal valor;

    @JsonProperty("description")
    private String descricao;

    @JsonProperty("payment_method_id")
    private String metodoDePagamento;

    @JsonProperty("notification_url")
    private String enderecoNotificacao;

    private PagadorMP payer;

}
