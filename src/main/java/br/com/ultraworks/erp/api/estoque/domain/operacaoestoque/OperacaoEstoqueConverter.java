package br.com.ultraworks.erp.api.estoque.domain.operacaoestoque;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OperacaoEstoqueConverter implements AttributeConverter<OperacaoEstoque, String> {

    @Override
    public String convertToDatabaseColumn(OperacaoEstoque OperacaoEstoque) {
        if (OperacaoEstoque == null) {
            return null;
        }
        return OperacaoEstoque.getValue();
    }

    @Override
    public OperacaoEstoque convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return OperacaoEstoque.fromValue(value);
    }
}
