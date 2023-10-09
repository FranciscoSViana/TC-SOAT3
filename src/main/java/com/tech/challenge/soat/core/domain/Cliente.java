package com.tech.challenge.soat.core.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    private UUID id;

    private String nome;

    private String cpf;

    private String email;

    private String telefone;

    private LocalDateTime dataHoraCriacao;

    private LocalDateTime dataHoraAlteracao;

    private boolean situacao;


}
