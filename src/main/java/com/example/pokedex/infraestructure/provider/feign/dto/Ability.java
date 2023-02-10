package com.example.pokedex.infraestructure.provider.feign.dto;

import lombok.*;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Ability {

    private String name;
}
