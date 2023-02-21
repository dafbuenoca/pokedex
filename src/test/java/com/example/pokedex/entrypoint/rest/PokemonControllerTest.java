package com.example.pokedex.entrypoint.rest;

import com.example.pokedex.application.PokemonFinder;
import com.example.pokedex.domain.model.Pokemon;
import com.example.pokedex.samples.Sample;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PokemonControllerTest {

    @InjectMocks
    PokemonController pokemonController;

    @Mock
    PokemonFinder pokemonFinder;

    @Test
    public void testGetPokemon_whenAValidPokemonId_thenShouldBeAReturnAnPokemon(){
        Pokemon pokemon = Sample.createPokemon();
        when(pokemonFinder.getPokemon(anyInt())).thenReturn(pokemon);

        ResponseEntity<Pokemon> response = pokemonController.getPokemon(25);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getId(), pokemon.getId());
    }

    @Test
    public void testGetPokemon_whenAInValidPokemonId_thenShouldBeAReturnANoTFoundResponse(){

        ResponseEntity<Pokemon> response = pokemonController.getPokemon(-5);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

   @Test
   public void testGetAllPokemon_whenAValidPokemonId_thenShouldBeAReturnAnPokemonPage(){
       Pokemon pokemon = Sample.createPokemon();
       Pageable paging = PageRequest.of(0, 20);
       List <Pokemon> pokemonList = new ArrayList<>();
       pokemonList.add(pokemon);
       Page<Pokemon> pokemonPage = new PageImpl<>(pokemonList, paging, 1);
       when(pokemonFinder.getAllPokemon(paging)).thenReturn(pokemonPage);

       ResponseEntity<Page<Pokemon>> response = pokemonController.getAllPokemon(0, 20);

       assertEquals(response.getStatusCode(), HttpStatus.OK);
   }

    @Test
    public void testGetAllPokemon_whenAInValidPokemonId_thenShouldBeAReturnANotFoundResponse(){
        Pokemon pokemon = Sample.createPokemon();
        List <Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(pokemon);

        ResponseEntity<Page<Pokemon>> response = pokemonController.getAllPokemon(84, 12);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}