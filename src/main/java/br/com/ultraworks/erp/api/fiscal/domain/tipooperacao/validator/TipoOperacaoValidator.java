package br.com.ultraworks.erp.api.fiscal.domain.tipooperacao.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.tipooperacao.TipoOperacao;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class TipoOperacaoValidator implements ConstraintValidator<ValidaTipoOperacao, String> {

    private Set<String> codigosValidos;

    @Override
    public void initialize(ValidaTipoOperacao constraint) {
        codigosValidos = Arrays.stream(TipoOperacao.values())
                .map(TipoOperacao::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String tipoOperacaoCodigo, ConstraintValidatorContext context) {
        if (tipoOperacaoCodigo == null) {
            return false; // Permitir valores nulos, se necessário
        }

        boolean isValid = codigosValidos.contains(tipoOperacaoCodigo);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Código inválido para o campo 'tipoOperacao': " + tipoOperacaoCodigo + ". Os códigos aceitos são: " + codigosValidos)
                    .addConstraintViolation();
        }

        return isValid;
    }
}

