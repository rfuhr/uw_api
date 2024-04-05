package br.com.ultraworks.erp.api.fiscal.domain.tipoimpressaodanfe;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoImpressaoDanfeConverter implements AttributeConverter<TipoImpressaoDanfe, String> {

    @Override
    public String convertToDatabaseColumn(TipoImpressaoDanfe TipoOperacao) {
        if (TipoOperacao == null) {
            return null;
        }
        return TipoOperacao.getValue();
    }

    @Override
    public TipoImpressaoDanfe convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return TipoImpressaoDanfe.fromValue(value);
    }
}
