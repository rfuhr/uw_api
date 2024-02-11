package br.com.ultraworks.erp.core.validators.minlistsize;

import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MinSizeValidator implements ConstraintValidator<MinSize, List<?>> {
    private int min;

    @Override
    public void initialize(MinSize constraintAnnotation) {
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(List<?> list, ConstraintValidatorContext context) {
        return list != null && list.size() >= min;
    }
}