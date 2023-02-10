package com.example.pokedex.infraestructure.provider.feign.exception;

public class PokemonMapperException extends RuntimeException{

    public PokemonMapperException(String message, Throwable throwable){

        super(message, throwable);
    }

}
