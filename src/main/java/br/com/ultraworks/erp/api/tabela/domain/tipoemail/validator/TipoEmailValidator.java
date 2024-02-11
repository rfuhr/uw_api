package br.com.ultraworks.erp.api.tabela.domain.tipoemail.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.tipoemail.TipoEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class TipoEmailValidator implements ConstraintValidator<ValidaTipoEmail, String> {

    private Set<String> codigosValidos;

    @Override
    public void initialize(ValidaTipoEmail constraint) {
        codigosValidos = Arrays.stream(TipoEmail.values())
                .map(TipoEmail::getCodigo)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String tipoEmailCodigo, ConstraintValidatorContext context) {
        if (tipoEmailCodigo == null) {
            return true; // Permitir valores nulos, se necessário
        }

        boolean isValid = codigosValidos.contains(tipoEmailCodigo.toUpperCase());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Código inválido para o campo 'tipoEmail': " + tipoEmailCodigo + ". Os códigos aceitos são: " + codigosValidos)
                    .addConstraintViolation();
        }

        return isValid;
    }
}

