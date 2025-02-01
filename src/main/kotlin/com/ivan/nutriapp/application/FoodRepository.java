package com.ivan.nutriapp.application;

import com.ivan.nutriapp.domain.nutrition.foodper100grams.FoodPer100Grams;
import com.ivan.nutriapp.domain.nutrition.FoodId;
import com.ivan.nutriapp.infrastructure.repositories.recipe.RecipeEntity;

public interface FoodRepository {

    FoodPer100Grams findById(FoodId id);
}
