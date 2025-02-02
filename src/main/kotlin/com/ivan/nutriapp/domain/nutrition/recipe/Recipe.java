package com.ivan.nutriapp.domain.nutrition.recipe;

import com.ivan.nutriapp.domain.nutrition.Gram;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor @Getter
public class Recipe {

    private final RecipeId id;
    private final RecipeName name;
    private final List<Ingredient> ingredients;

    public Recipe addIngredient(Ingredient ingredient) {

        var newIngredients = new ArrayList<>(ingredients);
        newIngredients.add(ingredient);

        return new Recipe(id, name, newIngredients);
    }

    public Recipe modifyQuantityOfIngredient(IngredientId ingredientId, Gram newQuantity) {

        var newIngredients = ingredients.stream().map(it ->
            it.getId().equals(ingredientId) ? it.modifyQuantity(newQuantity) : it
        ).toList();

        return new Recipe(id, name, newIngredients);
    }

    public List<TotalNutrient>nutrients() {

        return ingredients.stream()
            .flatMap( it -> it.nutrients().stream() )
            .collect(Collectors.groupingBy(TotalNutrient::getNutrientId))
            .values().stream()
            .map(it -> it.stream().reduce(TotalNutrient::plus).orElseThrow()).toList();
    }

    public Gram weight() {

        return ingredients.stream()
            .map(Ingredient::getQuantity)
            .reduce(Gram::plus)
            .orElse(Gram.zero());
    }
}
