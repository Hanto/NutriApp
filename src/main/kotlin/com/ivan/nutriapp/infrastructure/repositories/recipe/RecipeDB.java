package com.ivan.nutriapp.infrastructure.repositories.recipe;

import com.ivan.nutriapp.domain.nutrition.recipe.Recipe;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

public interface RecipeDB extends JpaRepository<RecipeEntity, String> {

    @EntityGraph( attributePaths = { "ingredients", "ingredients.food", "ingredients.food.nutrients" } )
    @Override @NotNull Optional<RecipeEntity> findById(String id);
}
