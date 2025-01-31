package com.ivan.nutriapp.application;

import com.ivan.nutriapp.domain.nutrition.recipe.Recipe;

public interface RecipeRepository {

    void save(Recipe recipe);
}
