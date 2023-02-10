package com.example.pokedex.domain.model;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder(setterPrefix = "with")
public class Evolution {

    private int id;

    private String name;
}
