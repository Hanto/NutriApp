package com.ivan.nutriapp.infrastructure.repositories.recipe;

import com.ivan.nutriapp.domain.nutrition.FoodId;
import com.ivan.nutriapp.domain.nutrition.FoodName;
import com.ivan.nutriapp.domain.nutrition.Gram;
import com.ivan.nutriapp.domain.nutrition.foodper100grams.FoodPer100Grams;
import com.ivan.nutriapp.domain.nutrition.recipe.Ingredient;
import com.ivan.nutriapp.domain.nutrition.recipe.IngredientId;
import com.ivan.nutriapp.domain.nutrition.recipe.Recipe;
import com.ivan.nutriapp.domain.nutrition.recipe.RecipeId;
import com.ivan.nutriapp.domain.nutrition.recipe.RecipeName;

import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

public class RecipeAdapter {

    public RecipeEntity toEntity(Recipe recipe) {

        var recipeEntity = new RecipeEntity(
            recipe.getId().getValue().toString(),
            recipe.getName().getValue(),
            recipe.getIngredients().stream()
                .map(it -> toEntity(recipe.getId(), it))
                .collect(Collectors.toSet())
        );

        recipeEntity.getIngredients().forEach(it -> it.setRecipe(recipeEntity));

        return recipeEntity;
    }

    private IngredientEntity toEntity(RecipeId recipeId, Ingredient ingredient) {

        return new IngredientEntity(
            ingredient.getId().getValue().toString(),
            ingredient.getQuantity().getValue(),
            ingredient.getFoodPer100Grams().getId().getValue(),
            ingredient.getFoodPer100Grams().getName().getValue()
        );
    }

    public Recipe toDomain(RecipeEntity entity) {

        return new Recipe(
            new RecipeId(UUID.fromString(entity.getId())),
            new RecipeName(entity.getName()),
            entity.getIngredients().stream().map(this::toDomain).toList()
        );
    }

    public Ingredient toDomain(IngredientEntity entity) {

        return new Ingredient(
            new IngredientId(UUID.fromString(entity.getId())),
            new Gram(entity.getQuantity()),
            new FoodPer100Grams(
                new FoodId(entity.getFoodId()),
                new FoodName(entity.getFoodName()),
                Collections.emptyList()
            )
        );
    }
}
