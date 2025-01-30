package com.ivan.nutriapp.domain.recipe;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor @Getter
public class Recipe {

    private RecipeId id;
    private List<Ingredient> ingredients;

    public void calculateTotalCalories() {

    }
}
