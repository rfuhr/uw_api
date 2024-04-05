package br.com.ultraworks.erp.api.configuracao.domain.tipoemissao;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoEmissaoConverter implements AttributeConverter<TipoEmissao, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoEmissao tipoCertificado) {
        if (tipoCertificado == null) {
            return null;
        }
        return Integer.parseInt(tipoCertificado.getValue());
    }

    @Override
    public TipoEmissao convertToEntityAttribute(Integer value) {
        if (value == null) {
            return null;
        }
        return TipoEmissao.fromValue(Integer.toString(value));
    }
}
