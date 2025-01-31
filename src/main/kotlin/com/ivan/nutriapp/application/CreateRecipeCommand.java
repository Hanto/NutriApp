package com.ivan.nutriapp.application;

import com.ivan.nutriapp.domain.nutrition.FoodId;
import com.ivan.nutriapp.domain.nutrition.Gram;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor @Getter
public class CreateRecipeCommand {

    private List<IngredientCommand> ingredients;

    @AllArgsConstructor @Getter
    public static class IngredientCommand {

        private final Gram quantity;
        private final FoodId foodId;
    }
}
