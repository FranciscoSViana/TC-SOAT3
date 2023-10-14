package com.tech.challenge.soat.domain.models;

import com.tech.challenge.soat.domain.enums.TipoCategoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoModel {

    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TipoCategoria categoria;

    @Lob
    private byte[] imagem;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private LocalDateTime dataHoraCriacao;

    private LocalDateTime dataHoraAlteracao;

}
