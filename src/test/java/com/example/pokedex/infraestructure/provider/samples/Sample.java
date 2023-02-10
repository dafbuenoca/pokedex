
package com.example.pokedex.infraestructure.provider.samples;

import com.example.pokedex.domain.model.Evolution;
import com.example.pokedex.domain.model.Pokemon;
import com.example.pokedex.infraestructure.provider.feign.dto.*;

import java.util.ArrayList;
import java.util.List;

public class Sample {

    public static PokemonDTO createPokemonDTO(){

        List<PokemonTypeDTO> types = new ArrayList<>();
        types.add(PokemonTypeDTO.builder().withType(
                Type.builder()
                        .withName("electric")
                        .build()
        ).build());

        List<PokemonAbilityDTO> abilities = new ArrayList<>();
        abilities.add(PokemonAbilityDTO.builder()
                        .withAbility(
                                Ability.builder()
                                        .withName("static")
                                        .build()
                        )
                .build());

        return PokemonDTO.builder()
                .withId(25)
                .withName("pikachu")
                .withWeight("60")
                .withHeight("4")
                .withAbilities(abilities)
                .withTypes(types)
                .withSpriteDTO(
                        SpriteDTO.builder()
                                .withImage("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png")
                                .build()
                )
                .withUrl("https://pokeapi.co/api/v2/pokemon/25/")
                .build();
    }

    public static PokemonSpeciesDTO createPokemonSpeciesDTO(){

        ArrayList<DescriptionDTO> descriptionDTOS = new ArrayList<>();
        descriptionDTOS.add(DescriptionDTO.builder()
                        .withDescription("When several of\n" +
                                "these POKéMON\n" +
                                "gather, their\felectricity could\n" +
                                "build and cause\n" +
                                "lightning storms.")
                .build());
        return PokemonSpeciesDTO.builder()
                .withDescriptionDTOList(descriptionDTOS)
                .withEvolutionChainDTO(
                    EvolutionChainDTO.builder()
                            .withUrl("https://pokeapi.co/api/v2/evolution-chain/10/")
                            .build()
                )
                .build();
    }

    public static EvolutionChainDTO createEvolutionChain(){
        List<EvolutionDTO> evolutionDTOS = new ArrayList<>();
        List<EvolutionDTO> evolutionDTOS2 = new ArrayList<>();
        evolutionDTOS2.add(EvolutionDTO.builder()
                .withEvolutionsDTO(new ArrayList<>())
                .withSpeciesDTO(
                        SpeciesDTO.builder()
                                .withName("raichu")
                                .withUrl("https://pokeapi.co/api/v2/pokemon-species/26/")
                                .build())
                .build());
        evolutionDTOS.add(EvolutionDTO.builder()
                .withEvolutionsDTO(evolutionDTOS2)
                .withSpeciesDTO(
                        SpeciesDTO.builder()
                                .withName("pikachu")
                                .withUrl("https://pokeapi.co/api/v2/pokemon-species/25/")
                                .build())
                .build());
        return EvolutionChainDTO.builder()
                .withChainDTO(
                        ChainDTO.builder()
                                .withEvolutionsDTO(evolutionDTOS)
                                .build()
                )
                .build();
    }

    public static Pokemon createPokemon() {

        List<com.example.pokedex.domain.model.Ability> abilities = new ArrayList<>();
        List<com.example.pokedex.domain.model.Type> types = new ArrayList<>();
        List<Evolution> evolutions = new ArrayList<>();
        abilities.add(
                com.example.pokedex.domain.model.Ability.builder()
                        .withName("static")
                        .build()
        );
        types.add(
                com.example.pokedex.domain.model.Type.builder()
                        .withName("electric")
                        .build()
        );
        evolutions.add(
                Evolution.builder()
                        .withId(25)
                        .withName("pikachu")
                        .build()
        );
        evolutions.add(
                Evolution.builder()
                        .withId(26)
                        .withName("raichu")
                        .build()
        );
        return Pokemon.builder()
                .withId(25)
                .withName("pikachu")
                .withHeight("4")
                .withWeight("60")
                .withDescription("When several of\n" +
                        "these POKéMON\n" +
                        "gather, their\felectricity could\n" +
                        "build and cause\n" +
                        "lightning storms.")
                .withSprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png")
                .withEvolutions(evolutions)
                .withAbilities(abilities)
                .withTypes(types)
                .build();
    }
}
