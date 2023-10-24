package com.tech.challenge.soat.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteModel {
    @Id
    private UUID id;

    private String nome;

    private String cpf;

    private String email;

    private String telefone;

    @CreationTimestamp
    @JoinColumn(columnDefinition = "datetime")
    private LocalDateTime dataHoraCriacao;

    @UpdateTimestamp
    @JoinColumn(columnDefinition = "datetime")
    private LocalDateTime dataHoraAlteracao;

    private boolean situacao;

}
