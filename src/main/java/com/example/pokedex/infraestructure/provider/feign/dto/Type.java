package com.example.pokedex.infraestructure.provider.feign.dto;

import lombok.*;

@Getter
@Setter
@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class Type {

    private String name;
}
