package com.example.pokedex.infraestructure.provider.feign.dto;

import lombok.*;

@Getter
@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class SpeciesDTO {

    private String name;
    private String url;
}
