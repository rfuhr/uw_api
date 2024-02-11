package br.com.ultraworks.erp.core.validators;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import br.com.ultraworks.erp.core.exception.CustomValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Component
public class CustomValidator<D> {

    @Autowired
    private Validator validator;

    public void validate(D dto) {
        Set<ConstraintViolation<D>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            List<FieldError> fieldErrors = violations.stream()
                .map(violation -> new FieldError(dto.getClass().getName(), violation.getPropertyPath().toString(), violation.getMessage()))
                .collect(Collectors.toList());
            throw new CustomValidationException(fieldErrors);
        }
    }
}

