package br.com.ultraworks.erp.api.estoque.domain.tipodocumentoestoque.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.ultraworks.erp.api.estoque.domain.tipodocumentoestoque.TipoDocumentoEstoque;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoDocumentoEstoqueValidator implements ConstraintValidator<ValidaTipoDocumentoEstoque, String> {

    private Set<String> codigosValidos;

    @Override
    public void initialize(ValidaTipoDocumentoEstoque constraint) {
        codigosValidos = Arrays.stream(TipoDocumentoEstoque.values())
                .map(TipoDocumentoEstoque::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String tipoDocumentoEstoqueCodigo, ConstraintValidatorContext context) {
        if (tipoDocumentoEstoqueCodigo == null) {
            return true; // Permitir valores nulos, se necessário
        }

        boolean isValid = codigosValidos.contains(tipoDocumentoEstoqueCodigo);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Código inválido para o campo 'tipoDocumentoEstoque': " + tipoDocumentoEstoqueCodigo + ". Os códigos aceitos são: " + codigosValidos)
                    .addConstraintViolation();
        }

        return isValid;
    }
}