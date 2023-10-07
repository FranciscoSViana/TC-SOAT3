package com.tech.challenge.soat.core.domain;

import com.tech.challenge.soat.core.enumerator.TipoCategoria;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

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
