package br.com.ultraworks.erp.api.estoque.domain.tipodocumentoestoque;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoDocumentoEstoqueConverter implements AttributeConverter<TipoDocumentoEstoque, String> {

    @Override
    public String convertToDatabaseColumn(TipoDocumentoEstoque TipoDocumentoEstoque) {
        if (TipoDocumentoEstoque == null) {
            return null;
        }
        return TipoDocumentoEstoque.getValue();
    }

    @Override
    public TipoDocumentoEstoque convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return TipoDocumentoEstoque.fromValue(value);
    }
}
