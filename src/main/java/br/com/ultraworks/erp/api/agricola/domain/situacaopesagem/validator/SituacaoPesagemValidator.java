package br.com.ultraworks.erp.api.agricola.domain.situacaopesagem.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.situacaopesagem.SituacaoPesagem;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class SituacaoPesagemValidator implements ConstraintValidator<ValidaSituacaoPesagem, String> {

	private Set<String> codigosValidos;

	@Override
	public void initialize(ValidaSituacaoPesagem constraint) {
		codigosValidos = Arrays.stream(SituacaoPesagem.values()).map(SituacaoPesagem::getValue)
				.collect(Collectors.toSet());
	}

	@Override
	public boolean isValid(String situacaoPesagemCodigo, ConstraintValidatorContext context) {
		if (situacaoPesagemCodigo == null) {
			return false;
		}

		boolean isValid = codigosValidos.contains(situacaoPesagemCodigo.toUpperCase());

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Código inválido para o campo 'situacao': "
					+ situacaoPesagemCodigo + ". Os códigos aceitos são: " + codigosValidos).addConstraintViolation();
		}

		return isValid;
	}
}
