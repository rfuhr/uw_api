package br.com.ultraworks.erp.api.tabela.domain.grandezaMedida;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GrandezaMedidaConverter implements AttributeConverter<GrandezaMedida, String> {

    @Override
    public String convertToDatabaseColumn(GrandezaMedida grandezaMedida) {
        if (grandezaMedida == null) {
            return null;
        }
        return grandezaMedida.getValue();
    }

    @Override
    public GrandezaMedida convertToEntityAttribute(String codigo) {
        if (codigo == null) {
            return null;
        }
        return GrandezaMedida.fromValue(codigo);
    }
}
