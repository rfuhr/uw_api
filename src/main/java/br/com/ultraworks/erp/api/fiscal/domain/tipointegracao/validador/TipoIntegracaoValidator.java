package br.com.ultraworks.erp.api.fiscal.domain.tipointegracao.validador;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.tipointegracao.TipoIntegracao;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class TipoIntegracaoValidator implements ConstraintValidator<ValidaTipoIntegracao, String> {

    private Set<String> codigosValidos;

    @Override
    public void initialize(ValidaTipoIntegracao constraint) {
        codigosValidos = Arrays.stream(TipoIntegracao.values())
                .map(TipoIntegracao::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String tipoCodigo, ConstraintValidatorContext context) {
        if (tipoCodigo == null) {
            return false; // Permitir valores nulos, se necessário
        }

        boolean isValid = codigosValidos.contains(tipoCodigo);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Código inválido para o campo 'tipoIntegracao': " + tipoCodigo + ". Os códigos aceitos são: " + codigosValidos)
                    .addConstraintViolation();
        }

        return isValid;
    }
}

