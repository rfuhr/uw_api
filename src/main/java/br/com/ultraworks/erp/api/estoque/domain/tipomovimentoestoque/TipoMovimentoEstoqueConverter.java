package br.com.ultraworks.erp.api.estoque.domain.tipomovimentoestoque;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoMovimentoEstoqueConverter implements AttributeConverter<TipoMovimentoEstoque, String> {

    @Override
    public String convertToDatabaseColumn(TipoMovimentoEstoque TipoMovimentoEstoque) {
        if (TipoMovimentoEstoque == null) {
            return null;
        }
        return TipoMovimentoEstoque.getValue();
    }

    @Override
    public TipoMovimentoEstoque convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return TipoMovimentoEstoque.fromValue(value);
    }
}
