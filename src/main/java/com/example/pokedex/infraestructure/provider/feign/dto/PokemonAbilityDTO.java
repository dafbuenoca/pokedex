package com.example.pokedex.infraestructure.provider.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class PokemonAbilityDTO {

    Ability ability;


}
