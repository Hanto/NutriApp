package com.ivan.nutriapp.infrastructure.repositories.recipe;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity @Table(name = "NUTRIENT100") @DynamicUpdate
@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class NutrientPer100Entity {

    @Id private Integer id;
    private Integer nutrientId;
    private String name;
    private Float quantity;
    private String unit;

    @ManyToOne ( fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn( name = "FOOD100_ID")
    private FoodPer100Entity food100;
}
