package com.ivan.nutriapp.infrastructure.repositories.recipe;

import com.ivan.nutriapp.domain.nutrition.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

public interface RecipeDB extends JpaRepository<RecipeEntity, String> {

}
