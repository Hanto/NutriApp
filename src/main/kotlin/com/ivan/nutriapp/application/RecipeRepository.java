package com.ivan.nutriapp.application;

import com.ivan.nutriapp.domain.nutrition.recipe.Recipe;
import com.ivan.nutriapp.domain.nutrition.recipe.RecipeId;
import com.ivan.nutriapp.infrastructure.repositories.recipe.RecipeEntity;

import java.util.Optional;

public interface RecipeRepository {

    void save(Recipe recipe);
    Optional<Recipe> findById(RecipeId id);
}
