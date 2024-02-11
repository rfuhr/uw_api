package br.com.ultraworks.erp.api.tabela.domain.tipotelefone.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.tipoendereco.TipoEndereco;
import br.com.ultraworks.erp.api.tabela.domain.tipotelefone.TipoTelefone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class TipoTelefoneValidator implements ConstraintValidator<ValidaTipoTelefone, String> {

    private Set<String> codigosValidos;

    @Override
    public void initialize(ValidaTipoTelefone constraint) {
        codigosValidos = Arrays.stream(TipoTelefone.values())
                .map(TipoTelefone::getCodigo)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String tipoTelefoneCodigo, ConstraintValidatorContext context) {
        if (tipoTelefoneCodigo == null) {
            return true; // Permitir valores nulos, se necessário
        }

        boolean isValid = codigosValidos.contains(tipoTelefoneCodigo.toUpperCase());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Código inválido para o campo 'tipoTelefone': " + tipoTelefoneCodigo + ". Os códigos aceitos são: " + codigosValidos)
                    .addConstraintViolation();
        }

        return isValid;
    }
}

