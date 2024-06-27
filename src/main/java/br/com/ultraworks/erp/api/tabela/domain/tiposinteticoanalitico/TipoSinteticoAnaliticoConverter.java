package br.com.ultraworks.erp.api.tabela.domain.tiposinteticoanalitico;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoSinteticoAnaliticoConverter implements AttributeConverter<TipoSinteticoAnalitico, String> {

    @Override
    public String convertToDatabaseColumn(TipoSinteticoAnalitico tipo) {
        if (tipo == null) {
            return null;
        }
        return tipo.getValue();
    }

    @Override
    public TipoSinteticoAnalitico convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return TipoSinteticoAnalitico.fromValue(value);
    }
}
