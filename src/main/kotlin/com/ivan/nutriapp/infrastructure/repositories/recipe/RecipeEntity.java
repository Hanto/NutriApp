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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Set;

@Entity @Table(name = "RECIPE") @DynamicUpdate
@AllArgsConstructor @NoArgsConstructor @Setter @Getter
public class RecipeEntity {

    @Id private String id;
    private String name;
    @OneToMany( mappedBy = "recipe", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<IngredientEntity>ingredients;
}
