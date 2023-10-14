package com.tech.challenge.soat.core.applications.feign.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Identificacao {

    @JsonProperty("type")
    private String tipoDocumento;

    @JsonProperty("number")
    private String numeroDocumento;

}
