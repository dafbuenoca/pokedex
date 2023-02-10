package com.example.pokedex.infraestructure.provider.feign.exception;

public class EvolutionException  extends RuntimeException{

    public EvolutionException(String message, Throwable throwable){

        super(message, throwable);
    }
}
