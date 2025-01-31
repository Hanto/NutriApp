package com.ivan.nutriapp.domain.recipe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor @ToString @Getter
public class Nutrient {

    private final NutrientId nutrientId;
    private final NutrientName name;
    private final Quantity quantity;

    public Nutrient plus(Nutrient nutrient) {

        if (nutrientId.equals(nutrient.nutrientId)) {

            var totalQuantity = quantity.plus(nutrient.getQuantity());
            return new Nutrient(nutrientId, name, totalQuantity);
        }
        else throw new ArithmeticException("Nutrients are not of the same type");
    }
}
