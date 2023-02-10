package com.example.pokedex.application;

import com.example.pokedex.domain.model.Pokemon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PokemonFinder {
    Pokemon getPokemon(Integer pokemonId);

    Page<Pokemon> getAllPokemon(Pageable paging);
}
