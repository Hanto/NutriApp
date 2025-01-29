package com.ivan.nutriapp.domain;

import java.util.List;

public class Food {

    private FoodId id;
    private FoodName name;
    private List<Nutrient>nutrients;

    public static class Nutrient {

        private NutrientId nutrientId;
        private NutrientName name;
        private Quantity quantity;

    }
}
