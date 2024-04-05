package br.com.ultraworks.erp.api.fiscal.domain.finalidadenfe;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class FinalidadeNfeConverter implements AttributeConverter<FinalidadeNfe, String> {

    @Override
    public String convertToDatabaseColumn(FinalidadeNfe TipoOperacao) {
        if (TipoOperacao == null) {
            return null;
        }
        return TipoOperacao.getValue();
    }

    @Override
    public FinalidadeNfe convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return FinalidadeNfe.fromValue(value);
    }
}
