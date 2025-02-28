package com.ivan.nutriapp.infrastructure.repositories.recipe;

import com.ivan.nutriapp.domain.nutrition.recipe.RecipeId;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;

@Entity @Table(name = "INGREDIENT") @DynamicUpdate
@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class IngredientEntity {

    @Id private String id;
    private Float quantity;

    @ManyToOne ( fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn( name = "FOOD100_ID")
    private FoodPer100Entity food;

    @ManyToOne ( fetch = FetchType.LAZY )
    @JoinColumn( name = "RECIPE_ID")
    private RecipeEntity recipe;
}
