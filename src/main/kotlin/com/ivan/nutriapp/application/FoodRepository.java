package com.ivan.nutriapp.application;

import com.ivan.nutriapp.domain.recipe.FoodPer100Grams;
import com.ivan.nutriapp.domain.recipe.FoodId;

public interface FoodRepository {

    FoodPer100Grams findById(FoodId id);
}
