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
public class EnderecoMP {

    @JsonProperty("zip_code")
    private String cep;

    @JsonProperty("street_name")
    private String rua;

    @JsonProperty("street_number")
    private String numeroRua;

    @JsonProperty("neighborhood")
    private String neighborhood;

    @JsonProperty("city")
    private String cidade;

    @JsonProperty("federal_unit")
    private String uf;
}
