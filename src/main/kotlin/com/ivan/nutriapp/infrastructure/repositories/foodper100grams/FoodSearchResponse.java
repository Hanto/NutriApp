package com.ivan.nutriapp.infrastructure.repositories.foodper100grams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Data
public class FoodSearchResponse {

    private List<FoodSearchEntity>foods;

    @AllArgsConstructor @NoArgsConstructor @Data
    public static class FoodSearchEntity {

        private Integer fdcId;
        private String description;
        private List<FoodNutrientSearchEntity>foodNutrients;
    }

    @AllArgsConstructor @NoArgsConstructor @Data
    public static class FoodNutrientSearchEntity {

        private Integer foodNutrientId;
        private Float value;
        private Integer nutrientId;
        private String nutrientName;
        private String unitName;
    }
}
