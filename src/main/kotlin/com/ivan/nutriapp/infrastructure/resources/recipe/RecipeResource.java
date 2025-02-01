package com.ivan.nutriapp.infrastructure.resources.recipe;

import com.ivan.nutriapp.application.CreateRecipeCommand;
import com.ivan.nutriapp.application.RecipeUseCase;
import com.ivan.nutriapp.domain.nutrition.FoodId;
import com.ivan.nutriapp.domain.nutrition.Gram;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController @AllArgsConstructor
public class RecipeResource {

    private final RecipeResourceAdapter recipeResourceAdapter;
    private final RecipeUseCase recipeUseCase;

    @PostMapping("/api/recipe/")
    public RecipeDTO createRecipe(@RequestBody CreateRecipeRequestDTO recipeDTO) {

        var createRecipeCommand = new CreateRecipeCommand(recipeDTO.getIngredients().stream().map( it ->

            new CreateRecipeCommand.IngredientCommand(new Gram(it.getQuantity()), new FoodId(it.getFoodId()))

        ).toList());

        var recipe = recipeUseCase.createRecipe(createRecipeCommand);
        return recipeResourceAdapter.toResource(recipe);
    }
}
