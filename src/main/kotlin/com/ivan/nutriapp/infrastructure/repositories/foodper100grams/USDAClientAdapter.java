package com.ivan.nutriapp.infrastructure.repositories.foodper100grams;

import com.ivan.nutriapp.domain.nutrition.foodper100grams.FoodPer100Grams;
import com.ivan.nutriapp.domain.nutrition.FoodId;
import com.ivan.nutriapp.domain.nutrition.FoodName;
import com.ivan.nutriapp.domain.nutrition.foodper100grams.NutrientPer100Grams;
import com.ivan.nutriapp.domain.nutrition.NutrientId;
import com.ivan.nutriapp.domain.nutrition.NutrientName;
import com.ivan.nutriapp.domain.nutrition.Quantity;
import com.ivan.nutriapp.domain.nutrition.QuantityUnit;

import java.util.Collections;

public class USDAClientAdapter {

    // REQUEST:

    public USDAFindByNameRequest toFindByNameRequest(String name) {

        return new USDAFindByNameRequest(
            name,
            Collections.emptyList(),
            30,
            1,
            null,
            null,
            null,
            Collections.emptyList(),
            null,
            null
        );
    }

    // RESPONSE:

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
