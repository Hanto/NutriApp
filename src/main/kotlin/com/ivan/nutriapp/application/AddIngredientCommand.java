package com.ivan.nutriapp.application;

import com.ivan.nutriapp.domain.nutrition.FoodPer100GramsId;
import com.ivan.nutriapp.domain.nutrition.Gram;
import com.ivan.nutriapp.domain.nutrition.recipe.RecipeId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class AddIngredientCommand
{
    private RecipeId recipeId;
    private Gram quantity;
    private FoodPer100GramsId foodId;
}
