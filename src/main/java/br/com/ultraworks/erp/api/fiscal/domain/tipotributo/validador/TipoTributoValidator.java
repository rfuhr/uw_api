package br.com.ultraworks.erp.api.fiscal.domain.tipotributo.validador;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.tipotributo.TipoTributo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class TipoTributoValidator implements ConstraintValidator<ValidaTipoTributo, String> {

    private Set<String> codigosValidos;

    @Override
    public void initialize(ValidaTipoTributo constraint) {
        codigosValidos = Arrays.stream(TipoTributo.values())
                .map(TipoTributo::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String tipoTributoCodigo, ConstraintValidatorContext context) {
        if (tipoTributoCodigo == null) {
            return false; // Permitir valores nulos, se necessário
        }

        boolean isValid = codigosValidos.contains(tipoTributoCodigo);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Código inválido para o campo 'tipoOperacao': " + tipoTributoCodigo + ". Os códigos aceitos são: " + codigosValidos)
                    .addConstraintViolation();
        }

        return isValid;
    }
}

