package com.example.pokedex.infraestructure.provider.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class EvolutionDTO {

    @JsonProperty("evolves_to")
    private List<EvolutionDTO> evolutionsDTO;

    @JsonProperty("species")
    private SpeciesDTO speciesDTO;

}
