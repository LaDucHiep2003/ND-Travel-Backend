package com.javaweb.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.cfg.ConstraintDef;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class DobValidator implements ConstraintValidator<DobConstraint, LocalTime> {
    private int min;

    @Override
    public void initialize(DobConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(LocalTime localTime, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(localTime)) return true;

        long years = ChronoUnit.YEARS.between(localTime, LocalDate.now());

        return years >= min;
    }
}
