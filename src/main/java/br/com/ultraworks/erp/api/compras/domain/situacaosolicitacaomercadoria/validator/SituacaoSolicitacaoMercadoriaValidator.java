package br.com.ultraworks.erp.api.compras.domain.situacaosolicitacaomercadoria.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.compras.domain.situacaosolicitacaomercadoria.SituacaoSolicitacaoMercadoria;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class SituacaoSolicitacaoMercadoriaValidator implements ConstraintValidator<ValidaSituacaoSolicitacaoMercadoria, String> {

	private Set<String> codigosValidos;

	@Override
	public void initialize(ValidaSituacaoSolicitacaoMercadoria constraint) {
		codigosValidos = Arrays.stream(SituacaoSolicitacaoMercadoria.values()).map(SituacaoSolicitacaoMercadoria::getValue)
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
			context.buildConstraintViolationWithTemplate("Código inválido para o campo 'situacaoSolicitacaoMercadoria': "
					+ codigo + ". Os códigos aceitos são: " + codigosValidos)
					.addConstraintViolation();
		}

		return isValid;
	}
}
