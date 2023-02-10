package com.example.pokedex.infraestructure.provider.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class EvolutionChainDTO {

    @JsonProperty("chain")
    private ChainDTO chainDTO;

    private String url;
}
