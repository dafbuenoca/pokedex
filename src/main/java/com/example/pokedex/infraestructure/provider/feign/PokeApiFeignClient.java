package com.example.pokedex.infraestructure.provider.feign;


import com.example.pokedex.infraestructure.provider.feign.config.FeignClientConfig;
import com.example.pokedex.infraestructure.provider.feign.dto.EvolutionChainDTO;
import com.example.pokedex.infraestructure.provider.feign.dto.PokemonDTO;
import com.example.pokedex.infraestructure.provider.feign.dto.PokemonListDTO;
import com.example.pokedex.infraestructure.provider.feign.dto.PokemonSpeciesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "pokeapi", url = "https://pokeapi.co/api/v2", configuration = FeignClientConfig.class)
@Service
public interface PokeApiFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/pokemon/{id}", consumes = "application/json")
    PokemonDTO getPokemonById(@RequestParam("id") Integer id);

    @RequestMapping(method = RequestMethod.GET, value = "/pokemon-species/{id}", consumes = "application/json")
    PokemonSpeciesDTO getPokemonSpeciesById(@RequestParam("id") Integer id);

    @RequestMapping(method = RequestMethod.GET, value = "/evolution-chain/{id}", consumes = "application/json")
    EvolutionChainDTO getEvolutionChainById(@RequestParam("id") Integer id);

    @RequestMapping(method = RequestMethod.GET, value = "/pokemon?limit={pageSize}&offset={offset}", consumes = "application/json", produces = "application/json")
    PokemonListDTO getAllPokemon(@RequestParam int offset, @RequestParam int pageSize);

}
