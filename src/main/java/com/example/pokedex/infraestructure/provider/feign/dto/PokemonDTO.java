package com.example.pokedex.infraestructure.provider.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Data
@Builder(setterPrefix = "with")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PokemonDTO implements Serializable {

    @Id
    private int id;

    private String name;

    @OneToMany
    private List<PokemonTypeDTO> types;


    @JsonProperty("abilities")
    @OneToMany
    private List<PokemonAbilityDTO> abilities;

    private String height;

    private String weight;

    private String url;

    @JsonProperty("sprites")
    @OneToOne
    private SpriteDTO spriteDTO;
}
