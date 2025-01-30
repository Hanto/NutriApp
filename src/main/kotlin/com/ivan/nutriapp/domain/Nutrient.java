package com.ivan.nutriapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor @ToString @Getter
public class Nutrient {

    private final NutrientId nutrientId;
    private final NutrientName name;
    private final Quantity quantity;

}
