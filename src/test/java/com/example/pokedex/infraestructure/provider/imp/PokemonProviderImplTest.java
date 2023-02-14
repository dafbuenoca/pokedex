package com.example.pokedex.infraestructure.provider.imp;

import com.example.pokedex.domain.model.Pokemon;
import com.example.pokedex.infraestructure.provider.feign.PokeApiFeignClient;
import com.example.pokedex.infraestructure.provider.feign.dto.EvolutionChainDTO;
import com.example.pokedex.infraestructure.provider.feign.dto.PokemonDTO;
import com.example.pokedex.infraestructure.provider.feign.dto.PokemonListDTO;
import com.example.pokedex.infraestructure.provider.feign.dto.PokemonSpeciesDTO;
import com.example.pokedex.infraestructure.provider.samples.Sample;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PokemonProviderImplTest {

    @InjectMocks
    PokemonProviderImpl pokemonProvider;

    @Mock
    PokeApiFeignClient pokeApiFeignClient;

    @Test
    public  void testGetPokemon_whenAValidPokemonId_ThenReturnAPokemonObject(){

        PokemonDTO pokemonDTOSample = Sample.createPokemonDTO();
        PokemonSpeciesDTO pokemonSpeciesDTO = Sample.createPokemonSpeciesDTO();
        EvolutionChainDTO evolutionChainDTO = Sample.createEvolutionChain();
        Pokemon  pokemonExpected = Sample.createPokemon();

        when(pokeApiFeignClient.getPokemonById(anyInt())).thenReturn(pokemonDTOSample);
        when(pokeApiFeignClient.getPokemonSpeciesById(anyInt())).thenReturn(pokemonSpeciesDTO);
        when(pokeApiFeignClient.getEvolutionChainById(anyInt())).thenReturn(evolutionChainDTO);


        Pokemon pokemonActual = pokemonProvider.getPokemon(25);

        assertEquals(pokemonExpected.getId(), pokemonActual.getId());
    }

    @Test
    public void testGetPokemon_whenAnInvaidPokemonIdNumber_thenThrowRuntimeException(){

        when(pokeApiFeignClient.getPokemonById(anyInt())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, ()->pokemonProvider.getPokemon(60000));
    }

    @Test
    public void testGetALLPokemon_whenCallMethod_thenReturnAPokemonPage(){

        PokemonDTO pokemonDTOSample = Sample.createPokemonDTO();
        PokemonSpeciesDTO pokemonSpeciesDTO = Sample.createPokemonSpeciesDTO();
        EvolutionChainDTO evolutionChainDTO = Sample.createEvolutionChain();
        List<PokemonDTO> pokemonDTOList =new ArrayList<>();
        pokemonDTOList.add(pokemonDTOSample);
        PokemonListDTO pokemonListDTO = PokemonListDTO
                .builder()
                .withCount(1)
                .withResults(pokemonDTOList)
                .build();
        Pokemon  pokemonExpected = Sample.createPokemon();
        Pageable paging = PageRequest.of(0, 20);
        when(pokeApiFeignClient.getPokemonById(anyInt())).thenReturn(pokemonDTOSample);
        when(pokeApiFeignClient.getPokemonSpeciesById(anyInt())).thenReturn(pokemonSpeciesDTO);
        when(pokeApiFeignClient.getEvolutionChainById(anyInt())).thenReturn(evolutionChainDTO);
        when(pokeApiFeignClient.getAllPokemon((int)paging.getOffset(), paging.getPageSize())).thenReturn(pokemonListDTO);


        Page page = pokemonProvider.getAllPokemon(paging);
        Pokemon pokemonActual = (Pokemon) page.getContent().get(0);

        assertEquals(pokemonExpected.getId(), pokemonActual.getId());

    }
}