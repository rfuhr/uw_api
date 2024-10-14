package br.com.ultraworks.erp.api.agricola.domain.identificacaodocumentoagricola.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.identificacaodocumentoagricola.IdentificacaoDocumentoAgricola;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class IdentificacaoDocumentoAgricolaValidator
		implements ConstraintValidator<ValidaIdentificacaoDocumentoAgricola, String> {

	private Set<String> codigosValidos;

	@Override
	public void initialize(ValidaIdentificacaoDocumentoAgricola constraint) {
		codigosValidos = Arrays.stream(IdentificacaoDocumentoAgricola.values())
				.map(IdentificacaoDocumentoAgricola::getValue).collect(Collectors.toSet());
	}

	@Override
	public boolean isValid(String identificacaoDocumentoAgricolaCodigo, ConstraintValidatorContext context) {
		if (identificacaoDocumentoAgricolaCodigo == null) {
			return false;
		}

		boolean isValid = codigosValidos.contains(identificacaoDocumentoAgricolaCodigo.toUpperCase());

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Código inválido para o campo 'situacao': "
					+ identificacaoDocumentoAgricolaCodigo + ". Os códigos aceitos são: " + codigosValidos)
					.addConstraintViolation();
		}

		return isValid;
	}
}
