package br.com.ultraworks.erp.api.fiscal.domain.tipotransporte;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoTransporteConverter implements AttributeConverter<TipoTransporte, String> {

    @Override
    public String convertToDatabaseColumn(TipoTransporte tipoTransporte) {
        if (tipoTransporte == null) {
            return null;
        }
        return tipoTransporte.getValue();
    }

    @Override
    public TipoTransporte convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return TipoTransporte.fromValue(value);
    }
}
