package br.com.ultraworks.erp.api.tabela.domain.tipoobrigatoriedade.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.tipoobrigatoriedade.TipoObrigatoriedade;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class TipoObrigatoriedadeValidator implements ConstraintValidator<ValidaTipoObrigatoriedade, String> {

	private Set<String> codigosValidos;

	@Override
	public void initialize(ValidaTipoObrigatoriedade constraint) {
		codigosValidos = Arrays.stream(TipoObrigatoriedade.values()).map(TipoObrigatoriedade::getValue)
				.collect(Collectors.toSet());
	}

	@Override
	public boolean isValid(String tipoObrigatoriedadeCodigo, ConstraintValidatorContext context) {
		if (tipoObrigatoriedadeCodigo == null) {
			return true; // Permitir valores nulos, se necessário
		}

		boolean isValid = codigosValidos.contains(tipoObrigatoriedadeCodigo.toUpperCase());

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Código inválido para o campo 'tipoObrigatoriedade': "
					+ tipoObrigatoriedadeCodigo + ". Os códigos aceitos são: " + codigosValidos)
					.addConstraintViolation();
		}

		return isValid;
	}
}
