package com.ivan.nutriapp.infrastructure.resources.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class ModifyIngredientRequest {

    private String ingredientId;
    private Float quantity;
    private Integer foodId;
}
