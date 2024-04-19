package br.com.ultraworks.erp.api.fiscal.domain.meiopagamento;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MeioPagamentoConverter implements AttributeConverter<MeioPagamento, String> {

    @Override
    public String convertToDatabaseColumn(MeioPagamento TipoOperacao) {
        if (TipoOperacao == null) {
            return null;
        }
        return TipoOperacao.getValue();
    }

    @Override
    public MeioPagamento convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return MeioPagamento.fromValue(value);
    }
}
