package br.com.ultraworks.erp.api.fiscal.domain.tipopresencacomprador;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoPresencaCompradorConverter implements AttributeConverter<TipoPresencaComprador, String> {

    @Override
    public String convertToDatabaseColumn(TipoPresencaComprador TipoOperacao) {
        if (TipoOperacao == null) {
            return null;
        }
        return TipoOperacao.getValue();
    }

    @Override
    public TipoPresencaComprador convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return TipoPresencaComprador.fromValue(value);
    }
}
