package com.ivan.nutriapp.infrastructure.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class NutrientDTO
{
    private String id;
    private String name;
    private Float amount;
    private String quantityUnit;
}
