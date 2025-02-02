package com.ivan.nutriapp.application;

import com.ivan.nutriapp.domain.nutrition.FoodPer100GramsId;
import com.ivan.nutriapp.domain.nutrition.Gram;
import com.ivan.nutriapp.domain.nutrition.recipe.RecipeId;
import com.ivan.nutriapp.domain.nutrition.recipe.RecipeName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor @Getter
public class CreateRecipeCommand {

    private RecipeId id;
    private RecipeName name;
    private List<IngredientCommand> ingredients;

    @AllArgsConstructor @Getter
    public static class IngredientCommand {

        private final Gram quantity;
        private final FoodPer100GramsId foodId;
    }
}
