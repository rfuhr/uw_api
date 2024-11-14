package br.com.ultraworks.erp.api.compras.domain.situacaocotacaomercadoria;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SituacaoCotacaoMercadoriaConverter implements AttributeConverter<SituacaoCotacaoMercadoria, String> {

    @Override
    public String convertToDatabaseColumn(SituacaoCotacaoMercadoria situacao) {
        if (situacao == null) {
            return null;
        }
        return situacao.getValue();
    }

    @Override
    public SituacaoCotacaoMercadoria convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return SituacaoCotacaoMercadoria.fromValue(value);
    }
}
