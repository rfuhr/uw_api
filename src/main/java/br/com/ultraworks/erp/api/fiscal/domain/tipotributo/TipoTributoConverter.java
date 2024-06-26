package br.com.ultraworks.erp.api.fiscal.domain.tipotributo;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoTributoConverter implements AttributeConverter<TipoTributo, String> {

    @Override
    public String convertToDatabaseColumn(TipoTributo TipoTributo) {
        if (TipoTributo == null) {
            return null;
        }
        return TipoTributo.getValue();
    }

    @Override
    public TipoTributo convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return TipoTributo.fromValue(value);
    }
}
