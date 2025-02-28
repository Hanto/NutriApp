package com.ivan.nutriapp.domain.nutrition.foodper100grams;

import com.ivan.nutriapp.domain.nutrition.Gram;
import com.ivan.nutriapp.domain.nutrition.NutrientId;
import com.ivan.nutriapp.domain.nutrition.NutrientName;
import com.ivan.nutriapp.domain.nutrition.Quantity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor @ToString @Getter
public class NutrientPer100Grams {

    private final NutrientPer100GramsId id;
    private final NutrientId nutrientId;
    private final NutrientName name;
    private final Quantity quantity;

    public Quantity getQuantityPer(Gram grams) {

        var percent = grams.divide(new Gram(100f));
        return quantity.applyPercent(percent);
    }
}
