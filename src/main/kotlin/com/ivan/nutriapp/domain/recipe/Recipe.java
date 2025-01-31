package com.ivan.nutriapp.domain.recipe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor @Getter
public class Recipe {

    private RecipeId id;
    private List<Ingredient> ingredients;

    public List<Nutrient>nutrients() {

        return ingredients.stream()
            .flatMap( it -> it.nutrients().stream() )
            .collect(Collectors.groupingBy(Nutrient::getNutrientId))
            .values().stream()
            .map(it -> it.stream().reduce(Nutrient::plus).orElseThrow()).toList();
    }

    public Gram weight() {

        return ingredients.stream()
            .map(Ingredient::getQuantity)
            .reduce(Gram::plus)
            .orElse(Gram.zero());
    }

}
