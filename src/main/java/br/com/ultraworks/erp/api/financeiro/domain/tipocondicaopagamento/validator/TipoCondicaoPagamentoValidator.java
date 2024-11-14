package br.com.ultraworks.erp.api.financeiro.domain.tipocondicaopagamento.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.tipocondicaopagamento.TipoCondicaoPagamento;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class TipoCondicaoPagamentoValidator implements ConstraintValidator<ValidaTipoCondicaoPagamento, String> {

	private Set<String> codigosValidos;

	@Override
	public void initialize(ValidaTipoCondicaoPagamento constraint) {
		codigosValidos = Arrays.stream(TipoCondicaoPagamento.values()).map(TipoCondicaoPagamento::getValue)
				.collect(Collectors.toSet());
	}

	@Override
	public boolean isValid(String tipoCondicaoPagamentoCodigo, ConstraintValidatorContext context) {
		if (tipoCondicaoPagamentoCodigo == null) {
			return false;
		}

		boolean isValid = codigosValidos.contains(tipoCondicaoPagamentoCodigo.toUpperCase());

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Código inválido para o campo 'tipoCondicaoPagamento': "
					+ tipoCondicaoPagamentoCodigo + ". Os códigos aceitos são: " + codigosValidos)
					.addConstraintViolation();
		}

		return isValid;
	}
}
