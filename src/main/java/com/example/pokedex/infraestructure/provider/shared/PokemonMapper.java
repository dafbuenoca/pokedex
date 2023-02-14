package com.example.pokedex.infraestructure.provider.shared;

import com.example.pokedex.domain.model.Ability;
import com.example.pokedex.domain.model.Evolution;
import com.example.pokedex.domain.model.Pokemon;
import com.example.pokedex.domain.model.Type;
import com.example.pokedex.infraestructure.provider.feign.dto.*;
import com.example.pokedex.infraestructure.provider.feign.exception.EvolutionException;
import com.example.pokedex.infraestructure.provider.feign.exception.PokemonMapperException;

import java.util.ArrayList;
import java.util.List;

public class PokemonMapper {

    public static Pokemon toPokemon(final PokemonDTO pokemonDTO, final PokemonSpeciesDTO pokemonSpeciesDTO, final EvolutionChainDTO evolutionChainDTO){
        try {
            String description = (pokemonSpeciesDTO.getDescriptionDTOList().size() != 0)?
                    pokemonSpeciesDTO.getDescriptionDTOList().get(0).getDescription()
                    : "";
            List<Type> types = toTypes(pokemonDTO);
            List<Ability> abilities = toAbilities(pokemonDTO);
            List<Evolution> evolutions = getEvolutionChain(evolutionChainDTO);
            return Pokemon.builder()
                    .withId(pokemonDTO.getId())
                    .withName(pokemonDTO.getName())
                    .withTypes(types)
                    .withAbilities(abilities)
                    .withHeight(pokemonDTO.getHeight())
                    .withWeight(pokemonDTO.getWeight())
                    .withSprite(pokemonDTO.getSpriteDTO().getImage())
                    .withDescription(description)
                    .withEvolutions(evolutions)
                    .build();
        } catch (Exception e){
            throw  new PokemonMapperException ("Error to converting PokemonDTO to Pokemon, with pokemon id: "
                    + pokemonDTO.getId(), e);
        }
    }

    private static  List<Evolution> getEvolutionChain(final EvolutionChainDTO evolutionChainDTO){
        List <Evolution> evolutions = new ArrayList<>();
        List<EvolutionDTO> evolutionsDTO =  evolutionChainDTO.getChainDTO().getEvolutionsDTO();
        getEvolution(evolutions, evolutionsDTO);
        return  evolutions;
    }

    private static void getEvolution(List<Evolution> evolutions, List<EvolutionDTO> evolutionsDTO) {
        try{
            if (evolutionsDTO.size() > 0) {
                for (EvolutionDTO evolutionDTO : evolutionsDTO) {
                    Evolution evolution = Evolution.builder()
                            .withName(evolutionDTO.getSpeciesDTO().getName())
                            .withId(Integer.parseInt(evolutionDTO.getSpeciesDTO().getUrl().split("/")[6]))
                            .build();
                    evolutions.add(evolution);
                    getEvolution(evolutions, evolutionDTO.getEvolutionsDTO());
                }
            }
        } catch (Exception e){
            throw new EvolutionException("Error to get Evolution",e);
        }
    }

    private static List<Ability> toAbilities(PokemonDTO pokemonDTO) {
        List <Ability> abilities = new ArrayList<>();
        for (PokemonAbilityDTO pokemonAbilityDTO :  pokemonDTO.getAbilities()) {
            abilities.add(Ability.builder()
                    .withName(pokemonAbilityDTO.getAbility().getName())
                    .build());
        }
        return abilities;
    }

    private static List<Type> toTypes(final PokemonDTO pokemonDTO) {
        List <Type> types =  new ArrayList<>();

        for (PokemonTypeDTO pokemonTypeDTO :  pokemonDTO.getTypes()) {
            types.add(Type.builder()
                    .withName(pokemonTypeDTO.getType().getName())
                    .build());
        }
        return types;
    }
}
