package com.ivan.nutriapp.infrastructure.resources.foodper100grams;

import com.ivan.nutriapp.domain.nutrition.FoodId;
import com.ivan.nutriapp.infrastructure.repositories.foodper100grams.USDAFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodResource {

    @Autowired private USDAFoodRepository usdaFoodRepository;
    @Autowired private FoodResourceAdapter foodResourceAdapter;

    @GetMapping("/api/food/{id}")
    public FoodPer100GramsDTO findBy(@PathVariable Integer id) {

        var foodId = new FoodId(id);
        var food = usdaFoodRepository.findById(foodId);
        return foodResourceAdapter.toResource(food);
    }
}
