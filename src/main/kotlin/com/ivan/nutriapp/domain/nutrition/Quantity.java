package com.ivan.nutriapp.domain.nutrition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor @Getter @ToString
public class Quantity {

    private final Float amount;
    private final QuantityUnit unit;

    public Quantity applyPercent(float percent) {

        return new Quantity(amount * percent, unit);
    }

    public Quantity plus(Quantity other) {

        if (unit == other.unit) {
            return new Quantity(amount + other.amount, unit);
        }
        else throw new ArithmeticException("Quantities are not of the same unit");
    }
}
