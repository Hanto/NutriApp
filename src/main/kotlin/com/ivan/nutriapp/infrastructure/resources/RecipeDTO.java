package com.ivan.nutriapp.infrastructure.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Data
public class RecipeDTO {

    private String id;
    private Float weight;
    private List<IngredientDTO> ingredients;

    @AllArgsConstructor @NoArgsConstructor @Data
    public static class IngredientDTO {

        private Float quantity;
        private FoodDTO food;
    }
}
