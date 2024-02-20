package br.com.ultraworks.erp.api.fiscal.domain.tipocalculo;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoCalculoConverter implements AttributeConverter<TipoCalculo, String> {

    @Override
    public String convertToDatabaseColumn(TipoCalculo TipoCalculo) {
        if (TipoCalculo == null) {
            return null;
        }
        return TipoCalculo.getValue();
    }

    @Override
    public TipoCalculo convertToEntityAttribute(String codigo) {
        if (codigo == null) {
            return null;
        }
        return TipoCalculo.fromCodigo(codigo);
    }
}
