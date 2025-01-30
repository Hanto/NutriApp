package com.ivan.nutriapp.infrastructure.resources;

import com.ivan.nutriapp.domain.recipe.FoodId;
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
