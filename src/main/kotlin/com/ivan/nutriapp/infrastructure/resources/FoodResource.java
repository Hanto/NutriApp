package com.ivan.nutriapp.infrastructure.resources;

import com.ivan.nutriapp.domain.Food;
import com.ivan.nutriapp.domain.FoodId;
import com.ivan.nutriapp.infrastructure.repositories.FoodEntity;
import com.ivan.nutriapp.infrastructure.repositories.USDAFoodRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodResource {

    @Autowired private USDAFoodRepository usdaFoodRepository;
    @Autowired private FoodResourceAdapter foodResourceAdapter;

    @GetMapping("/api/food/{id}")
    public FoodDTO findBy(@PathVariable Integer id) {

        var foodId = new FoodId(id);
        var food = usdaFoodRepository.findById(foodId);
        return foodResourceAdapter.toResource(food);
    }
}
