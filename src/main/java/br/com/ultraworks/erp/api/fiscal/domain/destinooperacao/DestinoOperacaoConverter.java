package br.com.ultraworks.erp.api.fiscal.domain.destinooperacao;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DestinoOperacaoConverter implements AttributeConverter<DestinoOperacao, String> {

    @Override
    public String convertToDatabaseColumn(DestinoOperacao TipoOperacao) {
        if (TipoOperacao == null) {
            return null;
        }
        return TipoOperacao.getValue();
    }

    @Override
    public DestinoOperacao convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return DestinoOperacao.fromValue(value);
    }
}
