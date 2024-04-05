package br.com.ultraworks.erp.api.tabela.domain.tipoendereco.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.tipoendereco.TipoEndereco;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class TipoEnderecoValidator implements ConstraintValidator<ValidaTipoEndereco, String> {

    private Set<String> codigosValidos;

    @Override
    public void initialize(ValidaTipoEndereco constraint) {
        codigosValidos = Arrays.stream(TipoEndereco.values())
                .map(TipoEndereco::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String tipoEnderecoCodigo, ConstraintValidatorContext context) {
        if (tipoEnderecoCodigo == null) {
            return true; // Permitir valores nulos, se necessário
        }

        boolean isValid = codigosValidos.contains(tipoEnderecoCodigo.toUpperCase());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Código inválido para o campo 'tipoEndereco': " + tipoEnderecoCodigo + ". Os códigos aceitos são: " + codigosValidos)
                    .addConstraintViolation();
        }

        return isValid;
    }
}

