package com.example.pokedex.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(setterPrefix = "with")
public class Ability {

    private String name;

    private boolean isHidden;
}
