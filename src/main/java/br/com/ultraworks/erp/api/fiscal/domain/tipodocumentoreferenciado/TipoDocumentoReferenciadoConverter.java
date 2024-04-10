package br.com.ultraworks.erp.api.fiscal.domain.tipodocumentoreferenciado;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoDocumentoReferenciadoConverter implements AttributeConverter<TipoDocumentoReferenciado, String> {

    @Override
    public String convertToDatabaseColumn(TipoDocumentoReferenciado tipoDocumento) {
        if (tipoDocumento == null) {
            return null;
        }
        return tipoDocumento.getValue();
    }

    @Override
    public TipoDocumentoReferenciado convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return TipoDocumentoReferenciado.fromValue(value);
    }
}
