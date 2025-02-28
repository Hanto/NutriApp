package com.ivan.nutriapp.domain.nutrition.recipe;

import com.ivan.nutriapp.domain.nutrition.foodper100grams.FoodPer100Grams;
import com.ivan.nutriapp.domain.nutrition.Gram;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor @Getter
public class Ingredient {

    private final IngredientId id;
    private final Gram quantity;
    private final FoodPer100Grams foodPer100Grams;

    public Ingredient modifyQuantity(Gram newQuantity) {

        return new Ingredient(id, newQuantity, foodPer100Grams);
    }

    public Food getFood() {

        return new Food(
            foodPer100Grams.getId(),
            foodPer100Grams.getName(),
            nutrients()
        );
    }

    public List<TotalNutrient>nutrients() {

        return foodPer100Grams.getNutrients().stream().map( it ->

            new TotalNutrient(
                it.getNutrientId(),
                it.getName(),
                it.getQuantityPer(quantity)

        )).toList();
    }
}
