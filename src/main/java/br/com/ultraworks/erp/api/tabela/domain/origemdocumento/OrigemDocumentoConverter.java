package br.com.ultraworks.erp.api.tabela.domain.origemdocumento;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrigemDocumentoConverter implements AttributeConverter<OrigemDocumento, String> {

    @Override
    public String convertToDatabaseColumn(OrigemDocumento origemDocumento) {
        if (origemDocumento == null) {
            return null;
        }
        return origemDocumento.getValue();
    }

    @Override
    public OrigemDocumento convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return OrigemDocumento.fromValue(value);
    }
}
