package br.com.ultraworks.erp.api.tabela.domain.grandezaMedida.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.grandezaMedida.GrandezaMedida;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class GrandezaMedidaValidator implements ConstraintValidator<ValidaGrandezaMedida, String> {

	private Set<String> codigosValidos;

	@Override
	public void initialize(ValidaGrandezaMedida constraint) {
		codigosValidos = Arrays.stream(GrandezaMedida.values()).map(GrandezaMedida::getValue)
				.collect(Collectors.toSet());
	}

	@Override
	public boolean isValid(String grandezaMedidaCodigo, ConstraintValidatorContext context) {
		if (grandezaMedidaCodigo == null) {
			return true; // Permitir valores nulos, se necessário
		}

		boolean isValid = codigosValidos.contains(grandezaMedidaCodigo.toUpperCase());

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Código inválido para o campo 'grandezaMedida': "
					+ grandezaMedidaCodigo + ". Os códigos aceitos são: " + codigosValidos).addConstraintViolation();
		}

		return isValid;
	}
}
