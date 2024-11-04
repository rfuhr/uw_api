package br.com.ultraworks.erp.api.tabela.domain.statusautorizacao.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.statusautorizacao.StatusAutorizacao;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class StatusAutorizacaoValidator implements ConstraintValidator<ValidaStatusAutorizacao, String> {

	private Set<String> codigosValidos;

	@Override
	public void initialize(ValidaStatusAutorizacao constraint) {
		codigosValidos = Arrays.stream(StatusAutorizacao.values()).map(StatusAutorizacao::getValue)
				.collect(Collectors.toSet());
	}

	@Override
	public boolean isValid(String codigo, ConstraintValidatorContext context) {
		if (codigo == null) {
			return true; // Permitir valores nulos, se necessário
		}

		boolean isValid = codigosValidos.contains(codigo.toUpperCase());

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Código inválido para o campo 'statusAutorizacao': " + codigo
					+ ". Os códigos aceitos são: " + codigosValidos).addConstraintViolation();
		}

		return isValid;
	}
}
