package com.ivan.nutriapp.infrastructure.resources.recipe;

import com.ivan.nutriapp.domain.nutrition.FoodId;
import com.ivan.nutriapp.domain.nutrition.Gram;
import com.ivan.nutriapp.domain.nutrition.recipe.IngredientId;
import com.ivan.nutriapp.domain.nutrition.recipe.RecipeId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Getter
public class ModifyIngredientCommand {

    private RecipeId receipeId;
    private IngredientId ingredientId;
    private FoodId foodId;
    private Gram quantity;
}
