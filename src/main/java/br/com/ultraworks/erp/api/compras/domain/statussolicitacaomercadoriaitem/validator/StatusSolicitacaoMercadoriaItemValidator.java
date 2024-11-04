package br.com.ultraworks.erp.api.compras.domain.statussolicitacaomercadoriaitem.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.compras.domain.statussolicitacaomercadoriaitem.StatusSolicitacaoMercadoriaItem;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class StatusSolicitacaoMercadoriaItemValidator
		implements ConstraintValidator<ValidaStatusSolicitacaoMercadoriaItem, String> {

	private Set<String> codigosValidos;

	@Override
	public void initialize(ValidaStatusSolicitacaoMercadoriaItem constraint) {
		codigosValidos = Arrays.stream(StatusSolicitacaoMercadoriaItem.values())
				.map(StatusSolicitacaoMercadoriaItem::getValue).collect(Collectors.toSet());
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
					"Código inválido para o campo 'statusSolicitacaoMercadoriaItem': " + codigo
							+ ". Os códigos aceitos são: " + codigosValidos)
					.addConstraintViolation();
		}

		return isValid;
	}
}
