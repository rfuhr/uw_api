package br.com.ultraworks.erp.api.tabela.domain.indicadorformapagamento;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class IndicadorFormaPagamentoConverter implements AttributeConverter<IndicadorFormaPagamento, String> {

    @Override
    public String convertToDatabaseColumn(IndicadorFormaPagamento formaPagamento) {
        if (formaPagamento == null) {
            return null;
        }
        return formaPagamento.getValue();
    }

    @Override
    public IndicadorFormaPagamento convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return IndicadorFormaPagamento.fromValue(value);
    }
}
