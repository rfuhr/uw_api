package br.com.ultraworks.erp.api.tabela.domain.tipopessoa.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.tipopessoa.TipoPessoa;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class TipoPessoaValidator implements ConstraintValidator<ValidaTipoPessoa, String> {

    private Set<String> codigosValidos;

    @Override
    public void initialize(ValidaTipoPessoa constraint) {
        codigosValidos = Arrays.stream(TipoPessoa.values())
                .map(TipoPessoa::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String tipoPessoaCodigo, ConstraintValidatorContext context) {
        if (tipoPessoaCodigo == null) {
            return true; // Permitir valores nulos, se necessário
        }

        boolean isValid = codigosValidos.contains(tipoPessoaCodigo.toUpperCase());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Código inválido para o campo 'tipoPessoa': " + tipoPessoaCodigo + ". Os códigos aceitos são: " + codigosValidos)
                    .addConstraintViolation();
        }

        return isValid;
    }
}

