package com.ivan.nutriapp.infrastructure.resources.foodper100grams;

import com.ivan.nutriapp.domain.nutrition.FoodId;
import com.ivan.nutriapp.infrastructure.repositories.foodper100grams.USDAFoodRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @AllArgsConstructor
public class FoodPer100GramsResource {

    private final USDAFoodRepository usdaFoodRepository;
    private final FoodPer100GramsResourceAdapter foodResourceAdapter;

    @GetMapping("/api/food/{id}")
    public FoodPer100GramsDTO findBy(@PathVariable Integer id) {

        var foodId = new FoodId(id);
        var food = usdaFoodRepository.findById(foodId);
        return foodResourceAdapter.toResource(food);
    }

    @GetMapping("/api/food/search/{name}")
    public List<FoodPer100GramsDTO> search(@PathVariable String name) {

        var results = usdaFoodRepository.findByName(name);
        return results.stream().map(foodResourceAdapter::toResource).toList();
    }
}
