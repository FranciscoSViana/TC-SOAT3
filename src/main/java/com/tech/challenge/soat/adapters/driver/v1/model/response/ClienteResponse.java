package com.tech.challenge.soat.adapters.driver.v1.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;
}
