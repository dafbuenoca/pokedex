package com.example.pokedex.application.imp;

import com.example.pokedex.application.PokemonFinder;
import com.example.pokedex.domain.model.Pokemon;
import com.example.pokedex.infraestructure.provider.PokemonProvider;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PokemonFinderImp implements PokemonFinder {

    private PokemonProvider pokemonProvider;

    @Override
    public Pokemon getPokemon(Integer pokemonId) {
        return pokemonProvider.getPokemon(pokemonId);
    }

    @Override
    public Page<Pokemon> getAllPokemon(Pageable paging) {
        return pokemonProvider.getAllPokemon(paging);
    }
}
