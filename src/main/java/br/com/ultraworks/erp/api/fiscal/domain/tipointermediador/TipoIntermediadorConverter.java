package br.com.ultraworks.erp.api.fiscal.domain.tipointermediador;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoIntermediadorConverter implements AttributeConverter<TipoIntermediador, String> {

    @Override
    public String convertToDatabaseColumn(TipoIntermediador TipoOperacao) {
        if (TipoOperacao == null) {
            return null;
        }
        return TipoOperacao.getValue();
    }

    @Override
    public TipoIntermediador convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return TipoIntermediador.fromValue(value);
    }
}
