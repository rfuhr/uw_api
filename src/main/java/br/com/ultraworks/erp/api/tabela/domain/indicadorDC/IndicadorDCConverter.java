package br.com.ultraworks.erp.api.tabela.domain.indicadorDC;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class IndicadorDCConverter implements AttributeConverter<IndicadorDC, String> {

    @Override
    public String convertToDatabaseColumn(IndicadorDC tipoPessoa) {
        if (tipoPessoa == null) {
            return null;
        }
        return tipoPessoa.getValue();
    }

    @Override
    public IndicadorDC convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return IndicadorDC.fromValue(value);
    }
}
