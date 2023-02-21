package com.example.pokedex.entrypoint.rest;

import com.example.pokedex.application.PokemonFinder;
import com.example.pokedex.domain.model.Pokemon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RequiredArgsConstructor
@RestController
@RequestMapping("/pokemon")
@CrossOrigin
@Slf4j
public class PokemonController {

    @Autowired
    private PokemonFinder pokemonFinder;

    @GetMapping
    public ResponseEntity<Page<Pokemon>> getAllPokemon(@RequestParam(defaultValue = "0") int page
            , @RequestParam(defaultValue = "20") int size){

        Pageable paging = PageRequest.of(page, size);
        if(paging.getOffset() < 1008) {
            log.info("Get pokemon list page: " + page);
            Page<Pokemon> pokemonPage = pokemonFinder.getAllPokemon(paging);
            return ResponseEntity.ok(pokemonPage);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{pokemonId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pokemon> getPokemon(@PathVariable("pokemonId") Integer pokemonId){
        if (pokemonId > 0 &&  pokemonId <= 1008) {
            log.info("Get Pokemon with id: " + pokemonId);
            return ResponseEntity.ok(pokemonFinder.getPokemon(pokemonId));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
