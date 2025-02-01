package com.ivan.nutriapp.infrastructure.repositories.recipe;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Set;

@Entity @Table(name = "RECIPE") @DynamicUpdate
@AllArgsConstructor @NoArgsConstructor @Data
public class RecipeEntity {

    @Id private String id;
    private String name;
    @OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipeId")
    private Set<IngredientEntity>ingredients;
}
