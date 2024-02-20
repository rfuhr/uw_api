package br.com.ultraworks.erp.api.fiscal.domain.tipocalculo.validador;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = TipoCalculoValidator.class)
@Documented
public @interface ValidaTipoCalculo {
    String message() default "Tipo de Cálculo inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
