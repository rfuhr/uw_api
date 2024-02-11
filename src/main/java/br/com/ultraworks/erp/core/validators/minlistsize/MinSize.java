package br.com.ultraworks.erp.core.validators.minlistsize;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = MinSizeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MinSize {
    String message() default "A lista deve conter pelo menos {min} itens";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int min();
}
