package com.example.pokedex.infraestructure.provider.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class PokemonSpeciesDTO {

    @JsonProperty("flavor_text_entries")
    private List<DescriptionDTO> descriptionDTOList;

    @JsonProperty("evolution_chain")
    private EvolutionChainDTO evolutionChainDTO;

    private String url;
}
