package com.ivan.nutriapp.application;

import com.ivan.nutriapp.domain.recipe.Food;
import com.ivan.nutriapp.domain.recipe.FoodId;

public interface FoodRepository {

    Food findById(FoodId id);
}
