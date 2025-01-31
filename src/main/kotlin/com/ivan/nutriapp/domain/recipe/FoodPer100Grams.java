package com.ivan.nutriapp.domain.recipe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

// DATA for 100 GR
@AllArgsConstructor @ToString @Getter
public class FoodPer100Grams {

    private final FoodId id;
    private final FoodName name;
    private final List<NutrientPer100Grams>nutrients;
}
