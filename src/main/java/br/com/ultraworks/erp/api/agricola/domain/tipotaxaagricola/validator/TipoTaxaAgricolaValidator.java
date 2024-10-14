package br.com.ultraworks.erp.api.agricola.domain.tipotaxaagricola.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.tipotaxaagricola.TipoTaxaAgricola;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class TipoTaxaAgricolaValidator implements ConstraintValidator<ValidaTipoTaxaAgricola, String> {

	private Set<String> codigosValidos;

	@Override
	public void initialize(ValidaTipoTaxaAgricola constraint) {
		codigosValidos = Arrays.stream(TipoTaxaAgricola.values()).map(TipoTaxaAgricola::getValue)
				.collect(Collectors.toSet());
	}

	@Override
	public boolean isValid(String tipoTaxaCodigo, ConstraintValidatorContext context) {
		if (tipoTaxaCodigo == null) {
			return false;
		}

		boolean isValid = codigosValidos.contains(tipoTaxaCodigo.toUpperCase());

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Código inválido para o campo 'tipo de taxa agrícola': "
					+ tipoTaxaCodigo + ". Os códigos aceitos são: " + codigosValidos).addConstraintViolation();
		}

		return isValid;
	}
}
