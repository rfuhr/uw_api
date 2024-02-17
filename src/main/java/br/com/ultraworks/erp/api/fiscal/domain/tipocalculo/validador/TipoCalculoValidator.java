package br.com.ultraworks.erp.api.fiscal.domain.tipocalculo.validador;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.tipocalculo.TipoCalculo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class TipoCalculoValidator implements ConstraintValidator<ValidaTipoCalculo, String> {

    private Set<String> codigosValidos;

    @Override
    public void initialize(ValidaTipoCalculo constraint) {
        codigosValidos = Arrays.stream(TipoCalculo.values())
                .map(TipoCalculo::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String tipoCalculoCodigo, ConstraintValidatorContext context) {
        if (tipoCalculoCodigo == null) {
            return false; // Permitir valores nulos, se necessário
        }

        boolean isValid = codigosValidos.contains(tipoCalculoCodigo);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Código inválido para o campo 'tipoCalculo': " + tipoCalculoCodigo + ". Os códigos aceitos são: " + codigosValidos)
                    .addConstraintViolation();
        }

        return isValid;
    }
}

