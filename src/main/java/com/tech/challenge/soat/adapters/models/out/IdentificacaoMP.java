package com.tech.challenge.soat.adapters.models.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdentificacaoMP {

    @JsonProperty("type")
    private String tipoDocumento;

    @JsonProperty("number")
    private String numeroDocumento;

}
