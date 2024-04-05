package br.com.ultraworks.erp.api.tabela.domain.indicadoroperacao;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class IndicadorOperacaoConverter implements AttributeConverter<IndicadorOperacao, String> {

    @Override
    public String convertToDatabaseColumn(IndicadorOperacao indicadorOperacao) {
        if (indicadorOperacao == null) {
            return null;
        }
        return indicadorOperacao.getValue();
    }

    @Override
    public IndicadorOperacao convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return IndicadorOperacao.fromValue(value);
    }
}
