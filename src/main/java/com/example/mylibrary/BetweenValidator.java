package com.example.mylibrary;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// в нем будет логика для проверки года на вхождение в диапазон
public class BetweenValidator implements ConstraintValidator<Between, Integer> {
    private int from = 0;
    private int to = 0;

    @Override
    public void initialize(Between constraintAnnotation) {
        // нужен чтобы проинициализировать начальные параметры - from и to
        from = constraintAnnotation.from();
        to = constraintAnnotation.to();
    }

    @Override
    public boolean isValid(Integer l, ConstraintValidatorContext constraintValidatorContext) {
        return l != null && l >= from && l <= to;
    }
}
