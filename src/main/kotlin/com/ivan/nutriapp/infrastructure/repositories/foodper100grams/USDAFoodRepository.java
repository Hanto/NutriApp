package com.ivan.nutriapp.infrastructure.repositories.foodper100grams;

import com.ivan.nutriapp.application.FoodRepository;
import com.ivan.nutriapp.domain.nutrition.foodper100grams.FoodPer100Grams;
import com.ivan.nutriapp.domain.nutrition.FoodId;
import com.ivan.nutriapp.domain.nutrition.recipe.Food;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@AllArgsConstructor
public class USDAFoodRepository implements FoodRepository {

    private final USDAClient client;
    private final USDAClientAdapter adapter;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Cacheable("Food")
    @Override public FoodPer100Grams findById(FoodId id) {

        var response = client.findBy(id);
        return adapter.toDomain(response);
    }

    public List<FoodPer100Grams> findByName(String name) {

        var request = adapter.toFindByNameRequest(name);
        var result = client.findByName(request);
        return result.getFoods().stream().map(adapter::toDomain).toList();
    }
}
