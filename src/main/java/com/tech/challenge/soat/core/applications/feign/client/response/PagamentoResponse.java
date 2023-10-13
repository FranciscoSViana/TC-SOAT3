package com.tech.challenge.soat.core.applications.feign.client.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagamentoResponse {

    private String codigoPix;

    private String qrCode;

}
