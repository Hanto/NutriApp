package com.ivan.nutriapp.application;

import com.ivan.nutriapp.domain.nutrition.foodper100grams.FoodPer100Grams;
import com.ivan.nutriapp.domain.nutrition.FoodId;

public interface FoodRepository {

    FoodPer100Grams findById(FoodId id);
}
