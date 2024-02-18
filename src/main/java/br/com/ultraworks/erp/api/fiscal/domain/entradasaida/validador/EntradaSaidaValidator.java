package br.com.ultraworks.erp.api.fiscal.domain.entradasaida.validador;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.entradasaida.EntradaSaida;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class EntradaSaidaValidator implements ConstraintValidator<ValidaEntradaSaida, String> {

    private Set<String> codigosValidos;

    @Override
    public void initialize(ValidaEntradaSaida constraint) {
        codigosValidos = Arrays.stream(EntradaSaida.values())
                .map(EntradaSaida::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String entradaSaidaCodigo, ConstraintValidatorContext context) {
        if (entradaSaidaCodigo == null) {
            return true; // Permitir valores nulos, se necessário
        }

        boolean isValid = codigosValidos.contains(entradaSaidaCodigo);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Código inválido para o campo 'entradaSaida': " + entradaSaidaCodigo + ". Os códigos aceitos são: " + codigosValidos)
                    .addConstraintViolation();
        }

        return isValid;
    }
}

