package com.example.pokedex.infraestructure.provider.imp;

import com.example.pokedex.domain.model.Pokemon;
import com.example.pokedex.infraestructure.provider.PokemonProvider;
import com.example.pokedex.infraestructure.provider.feign.PokeApiFeignClient;
import com.example.pokedex.infraestructure.provider.feign.dto.EvolutionChainDTO;
import com.example.pokedex.infraestructure.provider.feign.dto.PokemonDTO;
import com.example.pokedex.infraestructure.provider.feign.dto.PokemonListDTO;
import com.example.pokedex.infraestructure.provider.feign.dto.PokemonSpeciesDTO;
import com.example.pokedex.infraestructure.provider.feign.exception.PokemonMapperException;
import com.example.pokedex.infraestructure.provider.shared.PokemonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PokemonProviderImpl implements PokemonProvider {

    @Autowired
    private PokeApiFeignClient pokeApiFeignClient;

    @Override
    public Pokemon getPokemon(Integer pokemonId) {

        try {
            PokemonDTO pokemonDTO = getPokemonDTO(pokemonId);
            PokemonSpeciesDTO pokemonSpeciesDTO = getPokemonSpeciesDTO(pokemonId);
            int chainEvolutionId = Integer.parseInt(pokemonSpeciesDTO.getEvolutionChainDTO().getUrl().split("/")[6]);
            EvolutionChainDTO evolutionChainDTO = getEvolutionChainDTO(chainEvolutionId);
            return PokemonMapper.toPokemon(pokemonDTO, pokemonSpeciesDTO, evolutionChainDTO);
        } catch(Exception e){
            throw  new PokemonMapperException("holi", e);
        }
    }

    @Cacheable(value = "evolution_chains", key = "#id")
    public EvolutionChainDTO getEvolutionChainDTO(int chainEvolutionId) {
        log.info("Fetching evolution chain from pokeapi.co whit id: " + chainEvolutionId);
        EvolutionChainDTO evolutionChainDTO = pokeApiFeignClient.getEvolutionChainById(chainEvolutionId);
        return evolutionChainDTO;
    }

    @Cacheable(value = "pokemon_species", key = "#id")
    public PokemonSpeciesDTO getPokemonSpeciesDTO(Integer pokemonId) {
        log.info("Fetching pokemon species from pokeapi.co whit id: " + pokemonId);
        PokemonSpeciesDTO pokemonSpeciesDTO = pokeApiFeignClient.getPokemonSpeciesById(pokemonId);
        return pokemonSpeciesDTO;
    }

    @Cacheable(value = "pokemons", key = "#id")
    public PokemonDTO getPokemonDTO(Integer pokemonId) {
        log.info("Fetching pokemon from pokeapi.co whit id: " + pokemonId);
        PokemonDTO pokemonDTO = pokeApiFeignClient.getPokemonById(pokemonId);
        return pokemonDTO;
    }

    @Override
    public Page<Pokemon> getAllPokemon(Pageable paging) {

        log.info("Fetching all pokemon from pokeapi.co");
        PokemonListDTO pokemonListDTOElement = pokeApiFeignClient.getAllPokemon((int)paging.getOffset(), paging.getPageSize());
        List<PokemonDTO> pokemonListDTO =  pokemonListDTOElement.getResults();
        List<Pokemon> pokemonList = getPokemonList(paging, pokemonListDTO);
        Page<Pokemon> pokemonPage =  new PageImpl<>(pokemonList, paging, 1008);
        return pokemonPage;
    }

    private List<Pokemon> getPokemonList(Pageable paging, List<PokemonDTO> pokemonListDTO) {
        List<Pokemon> pokemonList = new ArrayList<>();
        for (PokemonDTO pokemonDTO: pokemonListDTO) {
            pokemonList.add( getPokemon( Integer.parseInt(pokemonDTO.getUrl().split("/")[6] )));
        }
        return pokemonList;
    }


}
