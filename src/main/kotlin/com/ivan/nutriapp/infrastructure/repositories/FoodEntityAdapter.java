package com.ivan.nutriapp.infrastructure.repositories;

import com.ivan.nutriapp.domain.recipe.FoodPer100Grams;
import com.ivan.nutriapp.domain.recipe.FoodId;
import com.ivan.nutriapp.domain.recipe.FoodName;
import com.ivan.nutriapp.domain.recipe.NutrientPer100Grams;
import com.ivan.nutriapp.domain.recipe.NutrientId;
import com.ivan.nutriapp.domain.recipe.NutrientName;
import com.ivan.nutriapp.domain.recipe.Quantity;
import com.ivan.nutriapp.domain.recipe.QuantityUnit;

public class FoodEntityAdapter {

    public FoodPer100Grams toDomain(FoodEntity foodEntity) {

        var id = new FoodId(foodEntity.getFdcId());
        var name = new FoodName(foodEntity.getDescription());
        var nutrients = foodEntity.getFoodNutrients().stream().map(this::toDomain).toList();

        return new FoodPer100Grams(id, name, nutrients);
    }

    private NutrientPer100Grams toDomain(FoodEntity.FoodNutrientEntity foodNutrientEntity) {

        var id = new NutrientId(foodNutrientEntity.getNutrient().getId());
        var name = new NutrientName(foodNutrientEntity.getNutrient().getName());
        var quantity = new Quantity(foodNutrientEntity.getAmount(), toQuantityUnit(foodNutrientEntity.getNutrient().getUnitName()));

        return new NutrientPer100Grams(id, name, quantity);
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
