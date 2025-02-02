package com.ivan.nutriapp.infrastructure.resources.recipe;

import com.ivan.nutriapp.application.AddIngredientCommand;
import com.ivan.nutriapp.application.CreateRecipeCommand;
import com.ivan.nutriapp.application.RecipeUseCase;
import com.ivan.nutriapp.domain.nutrition.FoodPer100GramsId;
import com.ivan.nutriapp.domain.nutrition.Gram;
import com.ivan.nutriapp.domain.nutrition.recipe.IngredientId;
import com.ivan.nutriapp.domain.nutrition.recipe.Recipe;
import com.ivan.nutriapp.domain.nutrition.recipe.RecipeId;
import com.ivan.nutriapp.domain.nutrition.recipe.RecipeName;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController @AllArgsConstructor
public class RecipeResource {

    private final RecipeResourceAdapter recipeResourceAdapter;
    private final RecipeUseCase recipeUseCase;

    @PostMapping("/api/recipe/")
    public RecipeDTO createRecipe(@RequestBody CreateRecipeRequestDTO recipeDTO) {

        var createRecipeCommand = new CreateRecipeCommand(
            new RecipeId(UUID.fromString(recipeDTO.getId())),
            new RecipeName(recipeDTO.getName()),
            recipeDTO.getIngredients().stream().map( it ->
                new CreateRecipeCommand.IngredientCommand(new Gram(it.getQuantity()), new FoodPer100GramsId(it.getFoodId()))

        ).toList());

        var recipe = recipeUseCase.createRecipe(createRecipeCommand);
        return recipeResourceAdapter.toResource(recipe);
    }

    @PostMapping("/api/recipe/{id}/ingredient")
    public void addIngredient(@PathVariable String id, @RequestBody AddIngredientRequest request) {

        var addIngredientCommand = new AddIngredientCommand(
            new RecipeId(UUID.fromString(id)),
            new Gram(request.getQuanity()),
            new FoodPer100GramsId(request.getFoodId())
        );

        recipeUseCase.addIngredient(addIngredientCommand);
    }

    @PutMapping("/api/recipe/{id}/ingredient")
    public void modifyIngredient(@PathVariable String id, @RequestBody ModifyIngredientRequest request) {

        var modifyIngredientCommand = new ModifyIngredientCommand(
            new RecipeId(UUID.fromString(id)),
            new IngredientId(UUID.fromString(request.getIngredientId())),
            new FoodPer100GramsId(request.getFoodId()),
            new Gram(request.getQuantity())
        );

        recipeUseCase.modifyIngredient(modifyIngredientCommand);
    }

    @GetMapping("/api/recipe/{id}")
    public Optional<RecipeDTO> findBy(@PathVariable String id) {

        var recipeId = new RecipeId(UUID.fromString(id));
        return recipeUseCase.findBy(recipeId)
            .map(recipeResourceAdapter::toResource);
    }
}
