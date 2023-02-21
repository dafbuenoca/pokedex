package com.example.pokedex.infraestructure.provider.feign.dto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PokemonTypeDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private  int slot;

    @OneToOne
    private Type type;

}
