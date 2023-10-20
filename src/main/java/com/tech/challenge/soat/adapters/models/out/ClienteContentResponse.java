package com.tech.challenge.soat.adapters.models.out;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
public class ClienteContentResponse {
    private List<ClienteResponse> content;
}
