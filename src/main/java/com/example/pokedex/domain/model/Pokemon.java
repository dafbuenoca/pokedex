package com.example.pokedex.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder(setterPrefix = "with")
@Getter
public class Pokemon {
    private int id;

    private String name;

    private List<Type> types;

    private List<Ability> abilities;

    private String height;

    private String weight;

    private String sprite;

    private String description;

    private List<Evolution> evolutions;

}
