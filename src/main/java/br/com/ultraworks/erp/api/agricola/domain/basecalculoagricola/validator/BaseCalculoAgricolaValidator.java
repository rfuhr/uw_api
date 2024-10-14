package br.com.ultraworks.erp.api.agricola.domain.basecalculoagricola.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.basecalculoagricola.BaseCalculoAgricola;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class BaseCalculoAgricolaValidator implements ConstraintValidator<ValidaBaseCalculoAgricola, String> {

	private Set<String> codigosValidos;

	@Override
	public void initialize(ValidaBaseCalculoAgricola constraint) {
		codigosValidos = Arrays.stream(BaseCalculoAgricola.values()).map(BaseCalculoAgricola::getValue)
				.collect(Collectors.toSet());
	}

	@Override
	public boolean isValid(String origembaseCodigo, ConstraintValidatorContext context) {
		if (origembaseCodigo == null) {
			return false;
		}

		boolean isValid = codigosValidos.contains(origembaseCodigo.toUpperCase());

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Código inválido para o campo 'base de cálculo agrícola': "
					+ origembaseCodigo + ". Os códigos aceitos são: " + codigosValidos).addConstraintViolation();
		}

		return isValid;
	}
}
