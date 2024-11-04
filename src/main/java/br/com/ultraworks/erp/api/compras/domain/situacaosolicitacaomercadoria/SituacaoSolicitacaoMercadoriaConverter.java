package br.com.ultraworks.erp.api.compras.domain.situacaosolicitacaomercadoria;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SituacaoSolicitacaoMercadoriaConverter implements AttributeConverter<SituacaoSolicitacaoMercadoria, String> {

    @Override
    public String convertToDatabaseColumn(SituacaoSolicitacaoMercadoria situacao) {
        if (situacao == null) {
            return null;
        }
        return situacao.getValue();
    }

    @Override
    public SituacaoSolicitacaoMercadoria convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return SituacaoSolicitacaoMercadoria.fromValue(value);
    }
}
