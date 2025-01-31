package com.ivan.nutriapp.infrastructure.resources.foodper100grams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Data
public class FoodPer100GramsDTO {

    private String id;
    private String name;
    private List<NutrientPer100GramsDTO> nutrients;
}
