package br.com.ultraworks.erp.api.fiscal.domain.tipocomunicacaonfe;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoComunicacaoNfeConverter implements AttributeConverter<TipoComunicacaoNfe, String> {

    @Override
    public String convertToDatabaseColumn(TipoComunicacaoNfe tipo) {
        if (tipo == null) {
            return null;
        }
        return tipo.getValue();
    }

    @Override
    public TipoComunicacaoNfe convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return TipoComunicacaoNfe.fromValue(value);
    }
}
