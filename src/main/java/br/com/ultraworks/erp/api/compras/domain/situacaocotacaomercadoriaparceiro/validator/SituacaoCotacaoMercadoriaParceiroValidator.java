package br.com.ultraworks.erp.api.compras.domain.situacaocotacaomercadoriaparceiro.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.compras.domain.situacaocotacaomercadoriaparceiro.SituacaoCotacaoMercadoriaParceiro;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class SituacaoCotacaoMercadoriaParceiroValidator
		implements ConstraintValidator<ValidaSituacaoCotacaoMercadoriaParceiro, String> {

	private Set<String> codigosValidos;

	@Override
	public void initialize(ValidaSituacaoCotacaoMercadoriaParceiro constraint) {
		codigosValidos = Arrays.stream(SituacaoCotacaoMercadoriaParceiro.values())
				.map(SituacaoCotacaoMercadoriaParceiro::getValue).collect(Collectors.toSet());
	}

	@Override
	public boolean isValid(String codigo, ConstraintValidatorContext context) {
		if (codigo == null) {
			return true; // Permitir valores nulos, se necessário
		}

		boolean isValid = codigosValidos.contains(codigo.toUpperCase());

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(
					"Código inválido para o campo 'situacaoCotacaoMercadoriaParceiro': " + codigo
							+ ". Os códigos aceitos são: " + codigosValidos)
					.addConstraintViolation();
		}

		return isValid;
	}
}
