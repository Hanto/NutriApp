package com.ivan.nutriapp.infrastructure.resources.foodper100grams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class NutrientPer100GramsDTO
{
    private String id;
    private String nutriendId;
    private String name;
    private Float amount;
    private String quantityUnit;
}
