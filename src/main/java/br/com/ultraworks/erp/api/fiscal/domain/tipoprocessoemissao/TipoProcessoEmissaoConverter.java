package br.com.ultraworks.erp.api.fiscal.domain.tipoprocessoemissao;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoProcessoEmissaoConverter implements AttributeConverter<TipoProcessoEmissao, String> {

    @Override
    public String convertToDatabaseColumn(TipoProcessoEmissao TipoOperacao) {
        if (TipoOperacao == null) {
            return null;
        }
        return TipoOperacao.getValue();
    }

    @Override
    public TipoProcessoEmissao convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return TipoProcessoEmissao.fromValue(value);
    }
}
