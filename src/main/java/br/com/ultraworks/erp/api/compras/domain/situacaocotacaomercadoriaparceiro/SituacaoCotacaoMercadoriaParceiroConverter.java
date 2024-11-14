package br.com.ultraworks.erp.api.compras.domain.situacaocotacaomercadoriaparceiro;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SituacaoCotacaoMercadoriaParceiroConverter implements AttributeConverter<SituacaoCotacaoMercadoriaParceiro, String> {

    @Override
    public String convertToDatabaseColumn(SituacaoCotacaoMercadoriaParceiro situacao) {
        if (situacao == null) {
            return null;
        }
        return situacao.getValue();
    }

    @Override
    public SituacaoCotacaoMercadoriaParceiro convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return SituacaoCotacaoMercadoriaParceiro.fromValue(value);
    }
}
