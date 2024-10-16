package br.com.ultraworks.erp.api.fiscal.domain.tipointegracao;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoIntegracaoConverter implements AttributeConverter<TipoIntegracao, String> {

    @Override
    public String convertToDatabaseColumn(TipoIntegracao tipo) {
        if (tipo == null) {
            return null;
        }
        return tipo.getValue();
    }

    @Override
    public TipoIntegracao convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return TipoIntegracao.fromValue(value);
    }
}
