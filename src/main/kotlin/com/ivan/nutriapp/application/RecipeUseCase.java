package com.ivan.nutriapp.application;

import com.ivan.nutriapp.application.CreateRecipeCommand.IngredientCommand;
import com.ivan.nutriapp.domain.nutrition.recipe.Ingredient;
import com.ivan.nutriapp.domain.nutrition.recipe.Recipe;
import com.ivan.nutriapp.domain.nutrition.recipe.RecipeId;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@AllArgsConstructor
public class RecipeUseCase {

    private final FoodRepository foodRepository;
    private final RecipeRepository recipeRepository;
    private final Logger log = LoggerFactory.getLogger(RecipeUseCase.class);

    public Recipe createRecipe(CreateRecipeCommand command) {

        var id = new RecipeId(UUID.randomUUID());
        var ingredients = command.getIngredients().stream()
            .map( this::createIngredient).toList();

        var recipe = new Recipe(id, ingredients);

        log.info("Created recipe {}", recipe.nutrients());

        recipeRepository.save(recipe);

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
