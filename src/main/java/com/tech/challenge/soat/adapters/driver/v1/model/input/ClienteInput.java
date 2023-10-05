package com.tech.challenge.soat.adapters.driver.v1.model.input;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteInput {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;
}
