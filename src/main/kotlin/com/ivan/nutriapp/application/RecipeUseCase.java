package com.ivan.nutriapp.application;

import com.ivan.nutriapp.application.CreateRecipeCommand.IngredientCommand;
import com.ivan.nutriapp.domain.recipe.Ingredient;
import com.ivan.nutriapp.domain.recipe.Recipe;
import com.ivan.nutriapp.domain.recipe.RecipeId;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class RecipeUseCase {

    private final FoodRepository foodRepository;

    public Recipe createRecipe(CreateRecipeCommand command) {

        var id = new RecipeId(UUID.randomUUID());
        var ingredients = command.getIngredients().stream()
            .map( this::createIngredient).toList();

        var recipe = new Recipe(id, ingredients);

        // SAVE DATABASE

        return recipe;
    }

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    private Ingredient createIngredient(IngredientCommand command) {

        var quantity = command.getQuantity();
        var food = foodRepository.findById(command.getFoodId());

        return new Ingredient(quantity, food);
    }
}
