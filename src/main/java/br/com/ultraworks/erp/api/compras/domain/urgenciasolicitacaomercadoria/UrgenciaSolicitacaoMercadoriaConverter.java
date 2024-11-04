package br.com.ultraworks.erp.api.compras.domain.urgenciasolicitacaomercadoria;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UrgenciaSolicitacaoMercadoriaConverter implements AttributeConverter<UrgenciaSolicitacaoMercadoria, String> {

    @Override
    public String convertToDatabaseColumn(UrgenciaSolicitacaoMercadoria urgencia) {
        if (urgencia == null) {
            return null;
        }
        return urgencia.getValue();
    }

    @Override
    public UrgenciaSolicitacaoMercadoria convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return UrgenciaSolicitacaoMercadoria.fromValue(value);
    }
}
