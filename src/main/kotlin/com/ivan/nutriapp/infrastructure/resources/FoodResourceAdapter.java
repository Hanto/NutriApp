package com.ivan.nutriapp.infrastructure.resources;

import com.ivan.nutriapp.domain.recipe.Food;
import com.ivan.nutriapp.domain.recipe.FoodPer100Grams;
import com.ivan.nutriapp.domain.recipe.Ingredient;
import com.ivan.nutriapp.domain.recipe.Nutrient;
import com.ivan.nutriapp.domain.recipe.NutrientPer100Grams;
import com.ivan.nutriapp.domain.recipe.Recipe;
import com.ivan.nutriapp.infrastructure.resources.RecipeDTO.IngredientDTO;

public class FoodResourceAdapter {

    public RecipeDTO toResource(Recipe recipe) {

        return new RecipeDTO(
            recipe.getId().getValue().toString(),
            recipe.weight().getValue(),
            recipe.getIngredients().stream().map(this::toResource).toList());
    }

    private IngredientDTO toResource(Ingredient ingredient) {

        return new IngredientDTO(
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

    public NutrientDTO toResource(Nutrient nutrient) {

        return new NutrientDTO(
            String.valueOf(nutrient.getNutrientId().getValue()),
            nutrient.getName().getValue(),
            nutrient.getQuantity().getAmount(),
            nutrient.getQuantity().getUnit().name()
        );
    }

    public FoodDTO toResource(FoodPer100Grams food) {

        return new FoodDTO(
            String.valueOf(food.getId().getValue()),
            food.getName().getValue(),
            food.getNutrients().stream().map(this::toResource).toList() );
    }

    private NutrientDTO toResource(NutrientPer100Grams nutrient) {

        return new NutrientDTO(
            String.valueOf(nutrient.getNutrientId().getValue()),
            nutrient.getName().getValue(),
            nutrient.getQuantity().getAmount(),
            nutrient.getQuantity().getUnit().name()
        );
    }
}
