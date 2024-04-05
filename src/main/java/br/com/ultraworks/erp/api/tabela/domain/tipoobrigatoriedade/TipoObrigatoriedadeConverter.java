package br.com.ultraworks.erp.api.tabela.domain.tipoobrigatoriedade;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoObrigatoriedadeConverter implements AttributeConverter<TipoObrigatoriedade, String> {

    @Override
    public String convertToDatabaseColumn(TipoObrigatoriedade tipoObrigatoriedade) {
        if (tipoObrigatoriedade == null) {
            return null;
        }
        return tipoObrigatoriedade.getValue();
    }

    @Override
    public TipoObrigatoriedade convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return TipoObrigatoriedade.fromValue(value);
    }
}
