package com.ivan.nutriapp.domain.recipe;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class Gram {

    private final Float value;

    public static Gram zero() {

        return new Gram(0.0f);
    }

    public Gram plus(Gram gram) {

        return new Gram(gram.value + value);
    }

    public Float divide(Gram gram) {

        return value / gram.value;
    }
}
