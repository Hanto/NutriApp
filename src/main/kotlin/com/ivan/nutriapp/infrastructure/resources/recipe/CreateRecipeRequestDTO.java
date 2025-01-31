package com.ivan.nutriapp.infrastructure.resources.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Data
public class CreateRecipeRequestDTO {

    private List<IngredientDTO> ingredients;

    @AllArgsConstructor @Data
    public static class IngredientDTO {

        private final Float quantity;
        private final Integer foodId;
    }
}
