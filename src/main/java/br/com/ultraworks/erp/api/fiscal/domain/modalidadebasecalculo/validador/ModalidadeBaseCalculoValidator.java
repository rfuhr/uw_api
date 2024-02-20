package br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculo.validador;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculo.ModalidadeBaseCalculo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class ModalidadeBaseCalculoValidator implements ConstraintValidator<ValidaModalidadeBaseCalculo, String> {

    private Set<String> codigosValidos;

    @Override
    public void initialize(ValidaModalidadeBaseCalculo constraint) {
        codigosValidos = Arrays.stream(ModalidadeBaseCalculo.values())
                .map(ModalidadeBaseCalculo::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String modalidadeCodigo, ConstraintValidatorContext context) {
        if (modalidadeCodigo == null) {
            return true; // Permitir valores nulos, se necessário
        }

        boolean isValid = codigosValidos.contains(modalidadeCodigo);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Código inválido para o campo 'modalidadeBaseCalculo': " + modalidadeCodigo + ". Os códigos aceitos são: " + codigosValidos)
                    .addConstraintViolation();
        }

        return isValid;
    }
}

