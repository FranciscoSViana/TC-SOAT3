package com.tech.challenge.soat.domain.models;

import com.tech.challenge.soat.core.enumerator.TipoCategoria;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoModel {

    private UUID id;

    private TipoCategoria categoria;

    private byte[] imagem;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private LocalDateTime dataHoraCriacao;

    private LocalDateTime dataHoraAlteracao;

}
