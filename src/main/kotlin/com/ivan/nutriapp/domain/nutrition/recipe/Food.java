package com.ivan.nutriapp.domain.nutrition.recipe;

import com.ivan.nutriapp.domain.nutrition.FoodId;
import com.ivan.nutriapp.domain.nutrition.FoodName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor @ToString @Getter
public class Food {

    private final FoodId id;
    private final FoodName name;
    private final List<Nutrient> nutrients;
}
