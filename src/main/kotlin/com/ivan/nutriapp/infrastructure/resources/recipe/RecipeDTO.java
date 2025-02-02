package com.ivan.nutriapp.infrastructure.resources.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Data
public class RecipeDTO {

    private String id;
    private String name;
    private Float weight;
    private List<IngredientDTO> ingredients;

    @AllArgsConstructor @NoArgsConstructor @Data
    public static class IngredientDTO {

        private String id;
        private Float quantity;
        private FoodDTO food;
    }

    @AllArgsConstructor @NoArgsConstructor @Data
    public static class FoodDTO {

        private String id;
        private String name;
        private List<NutrientDTO> nutrients;
    }

    @NoArgsConstructor @AllArgsConstructor @Data
    public static class NutrientDTO {

        private String id;
        private String name;
        private Float amount;
        private String quantityUnit;
    }
}
