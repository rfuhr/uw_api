package br.com.ultraworks.erp.api.tabela.domain.tipoautorizacao;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoAutorizacaoConverter implements AttributeConverter<TipoAutorizacao, String> {

    @Override
    public String convertToDatabaseColumn(TipoAutorizacao tipoAutorizacao) {
        if (tipoAutorizacao == null) {
            return null;
        }
        return tipoAutorizacao.getValue();
    }

    @Override
    public TipoAutorizacao convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return TipoAutorizacao.fromValue(value);
    }
}
