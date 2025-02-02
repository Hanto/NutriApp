package com.ivan.nutriapp.domain.nutrition.recipe;

import com.ivan.nutriapp.domain.nutrition.Gram;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Recipe {

    private final RecipeId id;
    private final RecipeName name;
    private final List<Ingredient> ingredients;

    // CONSTRUCTOR (validation of invariants):
    //--------------------------------------------------------------------------------------------------------

    public Recipe(RecipeId id, RecipeName name, List<Ingredient> ingredients) {

        var hasDuplicateIngredients = ingredients.stream()
            .collect(Collectors.groupingBy(Ingredient::getId, Collectors.counting()))
            .values().stream().anyMatch(count -> count > 1);

        var hasDuplicatedFoods = ingredients.stream()
            .collect(Collectors.groupingBy(it -> it.getFood().getId(), Collectors.counting()))
            .values().stream().anyMatch(count -> count > 1);

        if (hasDuplicateIngredients)
            throw new IllegalArgumentException("Duplicate ingredients");

        if (hasDuplicatedFoods)
            throw new IllegalArgumentException("Duplicated foods");

        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

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
