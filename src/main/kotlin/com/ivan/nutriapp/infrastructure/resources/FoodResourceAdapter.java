package com.ivan.nutriapp.infrastructure.resources;

import com.ivan.nutriapp.domain.recipe.Food;
import com.ivan.nutriapp.domain.recipe.Ingredient;
import com.ivan.nutriapp.domain.recipe.Nutrient;
import com.ivan.nutriapp.domain.recipe.Recipe;
import com.ivan.nutriapp.infrastructure.resources.RecipeDTO.IngredientDTO;

public class FoodResourceAdapter {

    public RecipeDTO toResource(Recipe recipe) {

        return new RecipeDTO(
            recipe.getId().getValue().toString(),
            recipe.getIngredients().stream().map(this::toResource).toList());
    }

    private IngredientDTO toResource(Ingredient ingredient) {

        return new IngredientDTO(
            ingredient.getQuantity().getValor(),
            toResource(ingredient.getFood())
        );
    }

    public FoodDTO toResource(Food food) {

        return new FoodDTO(
            String.valueOf(food.getId().getValue()),
            food.getName().getValue(),
            food.getNutrients().stream().map(this::toResource).toList() );
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
