package com.ivan.nutriapp.domain.recipe;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class Ingredient {

    private final Gram quantity;
    private final Food food;
}
