package com.example.pokedex.infraestructure.provider.feign.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class PokemonListDTO {

    private int count;

    private List<PokemonDTO> results;
}
