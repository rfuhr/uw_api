package br.com.ultraworks.erp.api.estoque.domain.operacaoestoque.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.ultraworks.erp.api.estoque.domain.operacaoestoque.OperacaoEstoque;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OperacaoEstoqueValidator implements ConstraintValidator<ValidaOperacaoEstoque, String> {

    private Set<String> codigosValidos;

    @Override
    public void initialize(ValidaOperacaoEstoque constraint) {
        codigosValidos = Arrays.stream(OperacaoEstoque.values())
                .map(OperacaoEstoque::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String operacaoEstoqueCodigo, ConstraintValidatorContext context) {
        if (operacaoEstoqueCodigo == null) {
            return true; // Permitir valores nulos, se necessário
        }

        boolean isValid = codigosValidos.contains(operacaoEstoqueCodigo);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Código inválido para o campo 'operacaoEstoque': " + operacaoEstoqueCodigo + ". Os códigos aceitos são: " + codigosValidos)
                    .addConstraintViolation();
        }

        return isValid;
    }
}