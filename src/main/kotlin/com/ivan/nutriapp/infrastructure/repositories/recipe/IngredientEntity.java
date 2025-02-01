package com.ivan.nutriapp.infrastructure.repositories.recipe;

import com.ivan.nutriapp.domain.nutrition.recipe.RecipeId;
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
@NoArgsConstructor @Setter @Getter
public class IngredientEntity {

    public IngredientEntity(String id, Float quantity, Integer foodId, String foodName) {

        this.id = id;
        this.quantity = quantity;
        this.foodId = foodId;
        this.foodName = foodName;
    }

    @Id private String id;
    private Float quantity;
    private Integer foodId;
    private String foodName;
    @ManyToOne ( fetch = FetchType.LAZY )
    private RecipeEntity recipe;
}
