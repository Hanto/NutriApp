package com.ivan.nutriapp.domain.recipe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor @ToString @Getter
public class NutrientPer100Grams {

    private final NutrientId nutrientId;
    private final NutrientName name;
    private final Quantity quantity;

    public Quantity getQuantityPer(Gram grams) {

        var percent = grams.divide(new Gram(100f));
        return quantity.applyPercent(percent);
    }

}
