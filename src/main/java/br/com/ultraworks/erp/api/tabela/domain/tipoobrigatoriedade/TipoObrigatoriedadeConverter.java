package br.com.ultraworks.erp.api.tabela.domain.tipoobrigatoriedade;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoObrigatoriedadeConverter implements AttributeConverter<TipoObrigatoriedade, String> {

    @Override
    public String convertToDatabaseColumn(TipoObrigatoriedade tipoObrigatoriedade) {
        if (tipoObrigatoriedade == null) {
            return null;
        }
        return tipoObrigatoriedade.getCodigo();
    }

    @Override
    public TipoObrigatoriedade convertToEntityAttribute(String codigo) {
        if (codigo == null) {
            return null;
        }
        return TipoObrigatoriedade.fromCodigo(codigo);
    }
}
