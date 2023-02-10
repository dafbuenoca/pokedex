package com.example.pokedex.infraestructure.provider.feign;


import com.example.pokedex.infraestructure.provider.feign.config.FeignClientConfig;
import com.example.pokedex.infraestructure.provider.feign.dto.EvolutionChainDTO;
import com.example.pokedex.infraestructure.provider.feign.dto.PokemonDTO;
import com.example.pokedex.infraestructure.provider.feign.dto.PokemonListDTO;
import com.example.pokedex.infraestructure.provider.feign.dto.PokemonSpeciesDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@FeignClient(name = "pokeapi", url = "https://pokeapi.co/api/v2", configuration = FeignClientConfig.class)
@Service
public interface PokeApiFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/pokemon/{id}", consumes = "application/json")
    PokemonDTO getPokemonById(@RequestParam("id") Integer id);

    @RequestMapping(method = RequestMethod.GET, value = "/pokemon-species/{id}", consumes = "application/json")
    PokemonSpeciesDTO getPokemonSpeciesById(@RequestParam("id") Integer id);

    @RequestMapping(method = RequestMethod.GET, value = "/evolution-chain/{id}", consumes = "application/json")
    EvolutionChainDTO getEvolutionChainById(@RequestParam("id") Integer id);

    @RequestMapping(method = RequestMethod.GET, value = "/pokemon?limit=100000", consumes = "application/json", produces = "application/json")
    PokemonListDTO getAllPokemon();

}
