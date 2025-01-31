package com.ivan.nutriapp.infrastructure.resources;

import com.ivan.nutriapp.application.CreateRecipeCommand;
import com.ivan.nutriapp.application.CreateRecipeCommand.IngredientCommand;
import com.ivan.nutriapp.application.RecipeUseCase;
import com.ivan.nutriapp.domain.recipe.FoodId;
import com.ivan.nutriapp.domain.recipe.Gram;
import com.ivan.nutriapp.infrastructure.repositories.USDAFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodResource {

    @Autowired private USDAFoodRepository usdaFoodRepository;
    @Autowired private FoodResourceAdapter foodResourceAdapter;
    @Autowired private RecipeUseCase recipeUseCase;

    @GetMapping("/api/food/{id}")
    public FoodDTO findBy(@PathVariable Integer id) {

        var foodId = new FoodId(id);
        var food = usdaFoodRepository.findById(foodId);
        return foodResourceAdapter.toResource(food);
    }

    @PostMapping("/api/recipe/")
    public RecipeDTO createRecipe(@RequestBody CreateRecipeRequestDTO recipeDTO) {

        var createRecipeCommand = new CreateRecipeCommand(recipeDTO.getIngredients().stream().map( it ->

            new IngredientCommand(new Gram(it.getQuantity()), new FoodId(it.getFoodId()))

        ).toList());

        var recipe = recipeUseCase.createRecipe(createRecipeCommand);
        return foodResourceAdapter.toResource(recipe);
    }
}
