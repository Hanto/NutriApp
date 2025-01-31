package com.ivan.nutriapp.infrastructure.repositories.foodper100grams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter @ToString
public class FoodEntity {

    private Integer fdcId;
    private String description;
    private List<FoodNutrientEntity> foodNutrients;

    @NoArgsConstructor @AllArgsConstructor @Setter @Getter @ToString
    public static class FoodNutrientEntity {

        private Float amount;
        private NutrientEntity nutrient;
    }

    @NoArgsConstructor @AllArgsConstructor @Setter @Getter @ToString
    public static class NutrientEntity {

        private Integer id;
        private String name;
        private String unitName;
    }
}
