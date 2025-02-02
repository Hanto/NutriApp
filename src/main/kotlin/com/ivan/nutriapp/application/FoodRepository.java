package com.ivan.nutriapp.application;

import com.ivan.nutriapp.domain.nutrition.foodper100grams.FoodPer100Grams;
import com.ivan.nutriapp.domain.nutrition.FoodPer100GramsId;

public interface FoodRepository {

    FoodPer100Grams findById(FoodPer100GramsId id);
}
