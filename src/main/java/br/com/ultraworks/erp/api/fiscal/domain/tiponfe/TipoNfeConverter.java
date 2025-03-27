package br.com.ultraworks.erp.api.fiscal.domain.tiponfe;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoNfeConverter implements AttributeConverter<TipoNfe, String> {

    @Override
    public String convertToDatabaseColumn(TipoNfe tipo) {
        if (tipo == null) {
            return null;
        }
        return tipo.getValue();
    }

    @Override
    public TipoNfe convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return TipoNfe.fromValue(value);
    }
}
