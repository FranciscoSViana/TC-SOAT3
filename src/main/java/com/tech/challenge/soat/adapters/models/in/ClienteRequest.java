package com.tech.challenge.soat.adapters.models.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {

    private UUID uuid;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

}
