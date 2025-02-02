package com.ivan.nutriapp.infrastructure.repositories.foodper100grams;

import com.ivan.nutriapp.domain.nutrition.foodper100grams.FoodPer100Grams;
import com.ivan.nutriapp.domain.nutrition.FoodId;
import com.ivan.nutriapp.domain.nutrition.FoodName;
import com.ivan.nutriapp.domain.nutrition.foodper100grams.NutrientPer100Grams;
import com.ivan.nutriapp.domain.nutrition.NutrientId;
import com.ivan.nutriapp.domain.nutrition.NutrientName;
import com.ivan.nutriapp.domain.nutrition.Quantity;
import com.ivan.nutriapp.domain.nutrition.QuantityUnit;
import com.ivan.nutriapp.domain.nutrition.foodper100grams.NutrientPer100GramsId;

import java.util.Collections;
import java.util.List;

public class USDAClientAdapter {

    // REQUEST:
    //--------------------------------------------------------------------------------------------------------

    public USDAFindByNameRequest toFindByNameRequest(String name) {

        return new USDAFindByNameRequest(
            name,
            List.of("Foundation"),
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
    //--------------------------------------------------------------------------------------------------------

    public FoodPer100Grams toDomain(FoodSearchResponse.FoodSearchEntity foodSearchEntity) {

        var id = new FoodId(foodSearchEntity.getFdcId());
        var name = new FoodName(foodSearchEntity.getDescription());
        var nutrients = foodSearchEntity.getFoodNutrients().stream().map(this::toDomain).toList();

        return new FoodPer100Grams(id, name, nutrients);
    }

    public NutrientPer100Grams toDomain(FoodSearchResponse.FoodNutrientSearchEntity nutrientSearchEntity) {

        var id = new NutrientPer100GramsId(nutrientSearchEntity.getFoodNutrientId());
        var nutrientId = new NutrientId(nutrientSearchEntity.getNutrientId());
        var name = new NutrientName(nutrientSearchEntity.getNutrientName());
        var quantity = new Quantity(nutrientSearchEntity.getValue(), toQuantityUnit(nutrientSearchEntity.getUnitName()));

        return new NutrientPer100Grams(id, nutrientId, name, quantity);
    }

    // RESPONSE:
    //--------------------------------------------------------------------------------------------------------

    public FoodPer100Grams toDomain(FoodByIdEntity foodEntity) {

        var id = new FoodId(foodEntity.getFdcId());
        var name = new FoodName(foodEntity.getDescription());
        var nutrients = foodEntity.getFoodNutrients().stream().map(this::toDomain).toList();

        return new FoodPer100Grams(id, name, nutrients);
    }

    private NutrientPer100Grams toDomain(FoodByIdEntity.FoodNutrientByIdEntity foodNutrientEntity) {

        var id = new NutrientPer100GramsId(foodNutrientEntity.getId());
        var nutrientId = new NutrientId(foodNutrientEntity.getNutrient().getId());
        var name = new NutrientName(foodNutrientEntity.getNutrient().getName());
        var quantity = new Quantity(foodNutrientEntity.getAmount(), toQuantityUnit(foodNutrientEntity.getNutrient().getUnitName()));

        return new NutrientPer100Grams(id, nutrientId, name, quantity);
    }

    private QuantityUnit toQuantityUnit(String unitName) {

        return switch (unitName.toLowerCase()) {

            case "g" -> QuantityUnit.G;
            case "mg" -> QuantityUnit.MG;
            case "kcal" -> QuantityUnit.KCAL;
            case "iu" -> QuantityUnit.IU;
            case "ug" -> QuantityUnit.UG;
            case "kj" -> QuantityUnit.KJ;
            default -> throw new RuntimeException("Unknown nutrient.unitname: "+ unitName);
        };
    }
}
