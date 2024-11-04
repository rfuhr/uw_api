package br.com.ultraworks.erp.api.tabela.domain.statusautorizacao;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusAutorizacaoConverter implements AttributeConverter<StatusAutorizacao, String> {

    @Override
    public String convertToDatabaseColumn(StatusAutorizacao statusAutorizacao) {
        if (statusAutorizacao == null) {
            return null;
        }
        return statusAutorizacao.getValue();
    }

    @Override
    public StatusAutorizacao convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return StatusAutorizacao.fromValue(value);
    }
}
