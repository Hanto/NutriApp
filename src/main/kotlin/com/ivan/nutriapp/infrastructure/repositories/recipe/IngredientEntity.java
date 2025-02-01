package com.ivan.nutriapp.infrastructure.repositories.recipe;

import com.ivan.nutriapp.domain.nutrition.recipe.RecipeId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;

@Entity @Table(name = "INGREDIENT") @DynamicUpdate
@NoArgsConstructor @AllArgsConstructor @Data
public class IngredientEntity {

    @Id private String id;
    private Float quantity;
    private Integer foodId;
    private String foodName;
    @Column(nullable = false)
    private String recipeId; // FK
}
