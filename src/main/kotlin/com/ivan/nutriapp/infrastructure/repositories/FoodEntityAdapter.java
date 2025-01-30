package com.ivan.nutriapp.infrastructure.repositories;

import com.ivan.nutriapp.domain.recipe.Food;
import com.ivan.nutriapp.domain.recipe.FoodId;
import com.ivan.nutriapp.domain.recipe.FoodName;
import com.ivan.nutriapp.domain.recipe.Nutrient;
import com.ivan.nutriapp.domain.recipe.NutrientId;
import com.ivan.nutriapp.domain.recipe.NutrientName;
import com.ivan.nutriapp.domain.recipe.Quantity;
import com.ivan.nutriapp.domain.recipe.QuantityUnit;

public class FoodEntityAdapter {

    public Food toDomain(FoodEntity foodEntity) {

        var id = new FoodId(foodEntity.getFdcId());
        var name = new FoodName(foodEntity.getDescription());
        var nutrients = foodEntity.getFoodNutrients().stream().map(this::toDomain).toList();

        return new Food(id, name, nutrients);
    }

    private Nutrient toDomain(FoodEntity.FoodNutrientEntity foodNutrientEntity) {

        var id = new NutrientId(foodNutrientEntity.getNutrient().getId());
        var name = new NutrientName(foodNutrientEntity.getNutrient().getName());
        var quantity = new Quantity(foodNutrientEntity.getAmount(), toQuantityUnit(foodNutrientEntity.getNutrient().getUnitName()));

        return new Nutrient(id, name, quantity);
    }

    private QuantityUnit toQuantityUnit(String unitName) {

        return switch (unitName) {

            case "g" -> QuantityUnit.G;
            case "mg" -> QuantityUnit.MG;
            case "kcal" -> QuantityUnit.KCAL;
            case "IU" -> QuantityUnit.IU;
            default -> throw new RuntimeException("Unknown nutrient.unitname: "+ unitName);
        };
    }
}
