package br.com.ultraworks.erp.api.agricola.domain.origemromaneio.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.origemromaneio.OrigemRomaneio;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class OrigemRomaneioValidator implements ConstraintValidator<ValidaOrigemRomaneio, String> {

	private Set<String> codigosValidos;

	@Override
	public void initialize(ValidaOrigemRomaneio constraint) {
		codigosValidos = Arrays.stream(OrigemRomaneio.values()).map(OrigemRomaneio::getValue)
				.collect(Collectors.toSet());
	}

	@Override
	public boolean isValid(String origemRomaneioCodigo, ConstraintValidatorContext context) {
		if (origemRomaneioCodigo == null) {
			return false;
		}

		boolean isValid = codigosValidos.contains(origemRomaneioCodigo.toUpperCase());

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Código inválido para o campo 'origem': "
					+ origemRomaneioCodigo + ". Os códigos aceitos são: " + codigosValidos).addConstraintViolation();
		}

		return isValid;
	}
}
