package com.ivan.nutriapp.domain.recipe;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor @Getter
public class Ingredient {

    private final Gram quantity;
    private final FoodPer100Grams foodPer100Grams;

    public Food getFood() {

        return new Food(
            foodPer100Grams.getId(),
            foodPer100Grams.getName(),
            nutrients()
        );
    }

    public List<Nutrient>nutrients() {

        return foodPer100Grams.getNutrients().stream().map( it ->

            new Nutrient(
                it.getNutrientId(),
                it.getName(),
                it.getQuantityPer(quantity)

        )).toList();
    }
}
