package br.com.ultraworks.erp.api.agricola.domain.caracteristicacontratoagricola.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.caracteristicacontratoagricola.CaracteristicaContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.origemromaneio.OrigemRomaneio;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class CaracteristicaContratoAgricolaValidator implements ConstraintValidator<ValidaCaracteristicaContratoAgricola, String> {

	private Set<String> codigosValidos;

	@Override
	public void initialize(ValidaCaracteristicaContratoAgricola constraint) {
		codigosValidos = Arrays.stream(CaracteristicaContratoAgricola.values()).map(CaracteristicaContratoAgricola::getValue)
				.collect(Collectors.toSet());
	}

	@Override
	public boolean isValid(String codigo, ConstraintValidatorContext context) {
		if (codigo == null) {
			return false;
		}

		boolean isValid = codigosValidos.contains(codigo.toUpperCase());

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Código inválido para o campo 'característica contrato agrícola': "
					+ codigo + ". Os códigos aceitos são: " + codigosValidos).addConstraintViolation();
		}

		return isValid;
	}
}
