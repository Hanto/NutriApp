package com.ivan.nutriapp.infrastructure.resources.recipe;

import com.ivan.nutriapp.domain.nutrition.recipe.Food;
import com.ivan.nutriapp.domain.nutrition.recipe.Ingredient;
import com.ivan.nutriapp.domain.nutrition.recipe.Nutrient;
import com.ivan.nutriapp.domain.nutrition.recipe.Recipe;

public class RecipeResourceAdapter {

    public RecipeDTO toResource(Recipe recipe) {

        return new RecipeDTO(
            recipe.getId().getValue().toString(),
            recipe.weight().getValue(),
            recipe.getIngredients().stream().map(this::toResource).toList());
    }

    private RecipeDTO.IngredientDTO toResource(Ingredient ingredient) {

        return new RecipeDTO.IngredientDTO(
            ingredient.getQuantity().getValue(),
            toResource(ingredient.getFood())
        );
    }

    private FoodDTO toResource(Food food) {

        return new FoodDTO(
            String.valueOf(food.getId().getValue()),
            food.getName().getValue(),
            food.getNutrients().stream().map(this::toResource).toList());
    }

    private NutrientDTO toResource(Nutrient nutrient) {

        return new NutrientDTO(
            String.valueOf(nutrient.getNutrientId().getValue()),
            nutrient.getName().getValue(),
            nutrient.getQuantity().getAmount(),
            nutrient.getQuantity().getUnit().name()
        );
    }
}
