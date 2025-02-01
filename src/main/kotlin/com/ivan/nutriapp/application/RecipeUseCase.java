package com.ivan.nutriapp.application;

import com.ivan.nutriapp.application.CreateRecipeCommand.IngredientCommand;
import com.ivan.nutriapp.domain.nutrition.Gram;
import com.ivan.nutriapp.domain.nutrition.recipe.Ingredient;
import com.ivan.nutriapp.domain.nutrition.recipe.IngredientId;
import com.ivan.nutriapp.domain.nutrition.recipe.Recipe;
import com.ivan.nutriapp.domain.nutrition.recipe.RecipeId;
import com.ivan.nutriapp.infrastructure.resources.recipe.ModifyIngredientCommand;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class RecipeUseCase {

    private final FoodRepository foodRepository;
    private final RecipeRepository recipeRepository;
    private final Logger log = LoggerFactory.getLogger(RecipeUseCase.class);

    public Recipe createRecipe(CreateRecipeCommand command) {

        var id = command.getId();
        var name = command.getName();
        var ingredients = command.getIngredients().stream()
            .map( this::createIngredient).toList();

        var recipe = new Recipe(id, name, ingredients);

        log.info("Created recipe {}", recipe.nutrients());

        recipeRepository.save(recipe);

        return recipe;
    }

    public void addIngredient(AddIngredientCommand command) {

        var recipeOptional = recipeRepository.findById(command.getRecipeId());
        var recipe = recipeOptional.get();

        var ingredient = new Ingredient(
            new IngredientId(UUID.randomUUID()),
            new Gram(command.getQuantity().getValue()),
            foodRepository.findById(command.getFoodId())
        );

        var modifiedRecipe = recipe.addIngredient(ingredient);

        recipeRepository.save(modifiedRecipe);
    }

    public void modifyIngredient(ModifyIngredientCommand command) {

        var recipeOptional = recipeRepository.findById(command.getReceipeId());
        var recipe = recipeOptional.get();

        var modifiedRecipe = recipe.modifyQuantityOfIngredient(command.getIngredientId(), command.getQuantity());

        recipeRepository.save(modifiedRecipe);
    }

    public Optional<Recipe> findBy(RecipeId id) {

        return recipeRepository.findById(id);
    }

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    private Ingredient createIngredient(IngredientCommand command) {

        var id = new IngredientId(UUID.randomUUID());
        var food = foodRepository.findById(command.getFoodId());
        var quantity = command.getQuantity();

        return new Ingredient(id, quantity, food);
    }
}
