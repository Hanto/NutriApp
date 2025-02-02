package com.ivan.nutriapp.infrastructure.repositories.foodper100grams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter @ToString
public class FoodByIdEntity {

    private Integer fdcId;
    private String description;
    private List<FoodNutrientByIdEntity> foodNutrients;

    @NoArgsConstructor @AllArgsConstructor @Setter @Getter @ToString
    public static class FoodNutrientByIdEntity {

        private Integer id;
        private Float amount;
        private NutrientByIdEntity nutrient;
    }

    @NoArgsConstructor @AllArgsConstructor @Setter @Getter @ToString
    public static class NutrientByIdEntity {

        private Integer id;
        private String name;
        private String unitName;
    }
}
