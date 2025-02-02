package com.ivan.nutriapp.infrastructure.repositories.recipe;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Set;

@Entity @Table(name = "FOOD100") @DynamicUpdate
@AllArgsConstructor @NoArgsConstructor @Setter @Getter
public class FoodPer100Entity {

    @Id private Integer id;
    private String name;

    @OneToMany( mappedBy = "food100", fetch = FetchType.LAZY,
        cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<NutrientPer100Entity>nutrients;
}
