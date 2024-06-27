package br.com.ultraworks.erp.api.tabela.domain.tiposinteticoanalitico.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.tiposinteticoanalitico.TipoSinteticoAnalitico;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class TipoSinteticoAnaliticoValidator implements ConstraintValidator<ValidaTipoSinteticoAnalitico, String> {

    private Set<String> codigosValidos;

    @Override
    public void initialize(ValidaTipoSinteticoAnalitico constraint) {
        codigosValidos = Arrays.stream(TipoSinteticoAnalitico.values())
                .map(TipoSinteticoAnalitico::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String tipo, ConstraintValidatorContext context) {
        if (tipo == null) {
            return true; // Permitir valores nulos, se necessário
        }

        boolean isValid = codigosValidos.contains(tipo.toUpperCase());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Código inválido para o campo 'tipoSinteticoAnalitico': " + tipo + ". Os códigos aceitos são: " + codigosValidos)
                    .addConstraintViolation();
        }

        return isValid;
    }
}

