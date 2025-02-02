package com.ivan.nutriapp.domain.nutrition.recipe;

import com.ivan.nutriapp.domain.nutrition.NutrientId;
import com.ivan.nutriapp.domain.nutrition.NutrientName;
import com.ivan.nutriapp.domain.nutrition.Quantity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor @ToString @Getter
public class TotalNutrient {

    private final NutrientId nutrientId;
    private final NutrientName name;
    private final Quantity quantity;

    public TotalNutrient plus(TotalNutrient nutrient) {

        if (nutrientId.equals(nutrient.nutrientId)) {

            var totalQuantity = quantity.plus(nutrient.getQuantity());
            return new TotalNutrient(nutrientId, name, totalQuantity);
        }
        else throw new ArithmeticException("Nutrients are not of the same type");
    }
}
