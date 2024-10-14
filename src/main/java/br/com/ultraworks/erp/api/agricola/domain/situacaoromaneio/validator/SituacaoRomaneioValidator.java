package br.com.ultraworks.erp.api.agricola.domain.situacaoromaneio.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.situacaoromaneio.SituacaoRomaneio;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class SituacaoRomaneioValidator implements ConstraintValidator<ValidaSituacaoRomaneio, String> {

	private Set<String> codigosValidos;

	@Override
	public void initialize(ValidaSituacaoRomaneio constraint) {
		codigosValidos = Arrays.stream(SituacaoRomaneio.values()).map(SituacaoRomaneio::getValue)
				.collect(Collectors.toSet());
	}

	@Override
	public boolean isValid(String situacaoRomaneioCodigo, ConstraintValidatorContext context) {
		if (situacaoRomaneioCodigo == null) {
			return false;
		}

		boolean isValid = codigosValidos.contains(situacaoRomaneioCodigo.toUpperCase());

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Código inválido para o campo 'situacao': "
					+ situacaoRomaneioCodigo + ". Os códigos aceitos são: " + codigosValidos).addConstraintViolation();
		}

		return isValid;
	}
}
