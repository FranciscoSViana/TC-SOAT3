package com.tech.challenge.soat.core.applications.feign.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pagador {

    @JsonProperty("email")
    private String email;

    @JsonProperty("first_name")
    private String nome;

    @JsonProperty("last_name")
    private String sobreNome;

    private Identificacao identification;

    private Endereco address;


}
