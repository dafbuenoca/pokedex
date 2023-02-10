package com.example.pokedex.infraestructure.provider.feign.dto;

import lombok.*;

@Setter
@Getter
@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class PokemonTypeDTO {
    private  int slot;
    private Type type;

}
