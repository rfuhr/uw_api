package br.com.ultraworks.erp.api.fiscal.domain.seloipi;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SeloIpiConverter implements AttributeConverter<SeloIpi, String> {

    @Override
    public String convertToDatabaseColumn(SeloIpi seloIpi) {
        if (seloIpi == null) {
            return null;
        }
        return seloIpi.getValue();
    }

    @Override
    public SeloIpi convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return SeloIpi.fromValue(value);
    }
}
