package br.com.ultraworks.erp.api.fiscal.domain.tipoconsumidor;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoConsumidorConverter implements AttributeConverter<TipoConsumidor, String> {

    @Override
    public String convertToDatabaseColumn(TipoConsumidor TipoOperacao) {
        if (TipoOperacao == null) {
            return null;
        }
        return TipoOperacao.getValue();
    }

    @Override
    public TipoConsumidor convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return TipoConsumidor.fromValue(value);
    }
}
