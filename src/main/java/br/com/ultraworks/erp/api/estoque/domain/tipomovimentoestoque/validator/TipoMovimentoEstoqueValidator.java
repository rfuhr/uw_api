package br.com.ultraworks.erp.api.estoque.domain.tipomovimentoestoque.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.ultraworks.erp.api.estoque.domain.tipomovimentoestoque.TipoMovimentoEstoque;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoMovimentoEstoqueValidator implements ConstraintValidator<ValidaTipoMovimentoEstoque, String> {

    private Set<String> codigosValidos;

    @Override
    public void initialize(ValidaTipoMovimentoEstoque constraint) {
        codigosValidos = Arrays.stream(TipoMovimentoEstoque.values())
                .map(TipoMovimentoEstoque::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String tipoMovimentoEstoqueCodigo, ConstraintValidatorContext context) {
        if (tipoMovimentoEstoqueCodigo == null) {
            return true; // Permitir valores nulos, se necessário
        }

        boolean isValid = codigosValidos.contains(tipoMovimentoEstoqueCodigo);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Código inválido para o campo 'tipoMovimentoEstoque': " + tipoMovimentoEstoqueCodigo + ". Os códigos aceitos são: " + codigosValidos)
                    .addConstraintViolation();
        }

        return isValid;
    }
}