package com.example.pokedex.infraestructure.provider.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
@Builder(setterPrefix = "with")
public class PokemonDTO {

    private int id;

    private String name;

    private List<PokemonTypeDTO> types;

    @JsonProperty("abilities")
    private List<PokemonAbilityDTO> abilities;

    private String height;

    private String weight;

    private String url;

    @JsonProperty("sprites")
    private SpriteDTO spriteDTO;
}
