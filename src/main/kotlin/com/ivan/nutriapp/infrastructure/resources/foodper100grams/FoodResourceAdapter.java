package com.ivan.nutriapp.infrastructure.resources.foodper100grams;

import com.ivan.nutriapp.domain.nutrition.foodper100grams.FoodPer100Grams;
import com.ivan.nutriapp.domain.nutrition.foodper100grams.NutrientPer100Grams;

public class FoodResourceAdapter {

    // FOODPER100:
    //--------------------------------------------------------------------------------------------------------

    public FoodPer100GramsDTO toResource(FoodPer100Grams food) {

        return new FoodPer100GramsDTO(
            String.valueOf(food.getId().getValue()),
            food.getName().getValue(),
            food.getNutrients().stream().map(this::toResource).toList() );
    }

    private NutrientPer100GramsDTO toResource(NutrientPer100Grams nutrient) {

        return new NutrientPer100GramsDTO(
            String.valueOf(nutrient.getNutrientId().getValue()),
            nutrient.getName().getValue(),
            nutrient.getQuantity().getAmount(),
            nutrient.getQuantity().getUnit().name()
        );
    }
}
