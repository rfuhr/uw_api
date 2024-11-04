package br.com.ultraworks.erp.api.tabela.domain.statusautorizacao.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = StatusAutorizacaoValidator.class)
@Documented
public @interface ValidaStatusAutorizacao {
	String message() default "Status da autorização inválido";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
