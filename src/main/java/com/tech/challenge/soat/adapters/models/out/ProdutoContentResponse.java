package com.tech.challenge.soat.adapters.models.out;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
public class ProdutoContentResponse {
    private List<ProdutoResponse> content;
}
