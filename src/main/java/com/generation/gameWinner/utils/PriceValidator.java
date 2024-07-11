package com.generation.gameWinner.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriceValidator implements ConstraintValidator<ValidationPrice, Double> {
    @Override
    public boolean isValid(Double price, ConstraintValidatorContext context) {
        return price != null && price > 0;
    }

}
