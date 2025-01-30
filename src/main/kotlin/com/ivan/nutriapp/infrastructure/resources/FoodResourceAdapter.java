package com.ivan.nutriapp.infrastructure.resources;

import com.ivan.nutriapp.domain.Food;
import com.ivan.nutriapp.domain.Nutrient;

public class FoodResourceAdapter {

    public FoodDTO toResource(Food food) {

        return new FoodDTO(
            String.valueOf(food.getId().getValue()),
            food.getName().getValue(),
            food.getNutrients().stream().map(this::toResource).toList() );
    }

    private NutrientDTO toResource(Nutrient nutrient) {

        return new NutrientDTO(
            String.valueOf(nutrient.getNutrientId().getValue()),
            nutrient.getName().getValue(),
            nutrient.getQuantity().getAmount(),
            nutrient.getQuantity().getUnit().name()
        );
    }
}
