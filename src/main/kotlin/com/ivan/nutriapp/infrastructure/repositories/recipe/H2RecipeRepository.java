package com.ivan.nutriapp.infrastructure.repositories.recipe;

import com.ivan.nutriapp.application.RecipeRepository;
import com.ivan.nutriapp.domain.nutrition.recipe.Recipe;
import com.ivan.nutriapp.domain.nutrition.recipe.RecipeId;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class H2RecipeRepository implements RecipeRepository {

    private final RecipeDB recipeDB;
    private final RecipeAdapter recipeAdapter;

    @Override
    public void save(Recipe recipe) {

        var recipeEntity = recipeAdapter.toEntity(recipe);
        recipeDB.save(recipeEntity);
    }

    public Optional<Recipe> findById(RecipeId id) {

        var optionalRecipeEntity = recipeDB.findById(id.getValue().toString());
        return optionalRecipeEntity.map(recipeAdapter::toDomain);
    }
}
