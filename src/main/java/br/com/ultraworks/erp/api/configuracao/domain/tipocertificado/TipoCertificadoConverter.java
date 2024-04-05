package br.com.ultraworks.erp.api.configuracao.domain.tipocertificado;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoCertificadoConverter implements AttributeConverter<TipoCertificado, String> {

    @Override
    public String convertToDatabaseColumn(TipoCertificado tipoCertificado) {
        if (tipoCertificado == null) {
            return null;
        }
        return tipoCertificado.getValue();
    }

    @Override
    public TipoCertificado convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return TipoCertificado.fromValue(value);
    }
}
