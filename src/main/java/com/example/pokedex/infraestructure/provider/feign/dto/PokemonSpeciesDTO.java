package com.example.pokedex.infraestructure.provider.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PokemonSpeciesDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    @JsonProperty("flavor_text_entries")
    private List<DescriptionDTO> descriptionDTOList;

    @OneToOne
    @JsonProperty("evolution_chain")
    private EvolutionChainDTO evolutionChainDTO;

    private String url;
}
