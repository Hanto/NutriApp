package com.ivan.nutriapp.infrastructure.repositories.recipe;

import com.ivan.nutriapp.domain.nutrition.FoodId;
import com.ivan.nutriapp.domain.nutrition.FoodName;
import com.ivan.nutriapp.domain.nutrition.Gram;
import com.ivan.nutriapp.domain.nutrition.NutrientId;
import com.ivan.nutriapp.domain.nutrition.NutrientName;
import com.ivan.nutriapp.domain.nutrition.Quantity;
import com.ivan.nutriapp.domain.nutrition.QuantityUnit;
import com.ivan.nutriapp.domain.nutrition.foodper100grams.FoodPer100Grams;
import com.ivan.nutriapp.domain.nutrition.foodper100grams.NutrientPer100Grams;
import com.ivan.nutriapp.domain.nutrition.foodper100grams.NutrientPer100GramsId;
import com.ivan.nutriapp.domain.nutrition.recipe.Ingredient;
import com.ivan.nutriapp.domain.nutrition.recipe.IngredientId;
import com.ivan.nutriapp.domain.nutrition.recipe.Nutrient;
import com.ivan.nutriapp.domain.nutrition.recipe.Recipe;
import com.ivan.nutriapp.domain.nutrition.recipe.RecipeId;
import com.ivan.nutriapp.domain.nutrition.recipe.RecipeName;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class RecipeAdapter {

    // TO ENTITY:
    //--------------------------------------------------------------------------------------------------------

    public RecipeEntity toEntity(Recipe recipe) {

        Set<IngredientEntity> ingredientEntities = recipe.getIngredients().stream()
            .map(this::toEntity)
            .collect(Collectors.toSet());

        RecipeEntity recipeEntity = new RecipeEntity(
            recipe.getId().getValue().toString(),
            recipe.getName().getValue(),
            ingredientEntities,
            false
        );

        ingredientEntities.forEach(it -> it.setRecipe(recipeEntity));

        return recipeEntity;
    }

    private IngredientEntity toEntity(Ingredient ingredient) {

        return new IngredientEntity(
            ingredient.getId().getValue().toString(),
            ingredient.getQuantity().getValue(),
            toEntity(ingredient.getFoodPer100Grams()),
            null
        );
    }

    private FoodPer100Entity toEntity(FoodPer100Grams foodPer100Grams) {

        Set<NutrientPer100Entity>nutrients = foodPer100Grams.getNutrients().stream()
            .map(this::toEntity)
            .collect(Collectors.toSet());

        var foodPer100Entity = new FoodPer100Entity(
            foodPer100Grams.getId().getValue(),
            foodPer100Grams.getName().getValue(),
            nutrients
        );

        nutrients.forEach(it -> it.setFood100(foodPer100Entity));

        return foodPer100Entity;
    }

    private NutrientPer100Entity toEntity(NutrientPer100Grams nutrient) {

        return new NutrientPer100Entity(
            nutrient.getId().getValue(),
            nutrient.getNutrientId().getValue(),
            nutrient.getName().getValue(),
            nutrient.getQuantity().getAmount(),
            nutrient.getQuantity().getUnit().name(),
            null
        );
    }

    // TO DOMAIN:
    //--------------------------------------------------------------------------------------------------------

    public Recipe toDomain(RecipeEntity entity) {

        return new Recipe(
            new RecipeId(UUID.fromString(entity.getId())),
            new RecipeName(entity.getName()),
            entity.getIngredients().stream().map(this::toDomain).toList()
        );
    }

    public Ingredient toDomain(IngredientEntity entity) {

        return new Ingredient(
            new IngredientId(UUID.fromString(entity.getId())),
            new Gram(entity.getQuantity()),
            toDomain(entity.getFood())
        );
    }

    public FoodPer100Grams toDomain(FoodPer100Entity entity) {

        return new FoodPer100Grams(
            new FoodId(entity.getId()),
            new FoodName(entity.getName()),
            entity.getNutrients().stream().map(this::toDomain).toList()
        );
    }

    public NutrientPer100Grams toDomain(NutrientPer100Entity entity) {

        return new NutrientPer100Grams(
            new NutrientPer100GramsId(entity.getId()),
            new NutrientId(entity.getNutrientId()),
            new NutrientName(entity.getName()),
            new Quantity(entity.getQuantity(), toQuantityUnit(entity.getUnit()))
        );
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
