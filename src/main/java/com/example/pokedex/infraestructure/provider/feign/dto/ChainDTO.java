package com.example.pokedex.infraestructure.provider.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class ChainDTO {

    @JsonProperty("evolves_to")
    private List<EvolutionDTO> evolutionsDTO;


}
