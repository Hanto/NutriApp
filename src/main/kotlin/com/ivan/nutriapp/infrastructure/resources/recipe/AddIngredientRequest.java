package com.ivan.nutriapp.infrastructure.resources.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class AddIngredientRequest {
    private Float quanity;
    private Integer foodId;
}
