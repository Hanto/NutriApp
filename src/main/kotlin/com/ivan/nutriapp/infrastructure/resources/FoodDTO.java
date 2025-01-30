package com.ivan.nutriapp.infrastructure.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Data
public class FoodDTO {

    private String id;
    private String name;
    private List<NutrientDTO> nutrients;
}
