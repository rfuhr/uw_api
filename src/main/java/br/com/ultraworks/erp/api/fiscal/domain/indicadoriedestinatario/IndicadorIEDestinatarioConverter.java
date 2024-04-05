package br.com.ultraworks.erp.api.fiscal.domain.indicadoriedestinatario;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class IndicadorIEDestinatarioConverter implements AttributeConverter<IndicadorIEDestinatario, String> {

    @Override
    public String convertToDatabaseColumn(IndicadorIEDestinatario TipoOperacao) {
        if (TipoOperacao == null) {
            return null;
        }
        return TipoOperacao.getValue();
    }

    @Override
    public IndicadorIEDestinatario convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return IndicadorIEDestinatario.fromValue(value);
    }
}
