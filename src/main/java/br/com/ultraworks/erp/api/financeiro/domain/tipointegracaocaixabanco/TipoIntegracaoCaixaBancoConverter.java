package br.com.ultraworks.erp.api.financeiro.domain.tipointegracaocaixabanco;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoIntegracaoCaixaBancoConverter implements AttributeConverter<TipoIntegracaoCaixaBanco, String> {

    @Override
    public String convertToDatabaseColumn(TipoIntegracaoCaixaBanco tipoIntegracaoCaixaBanco) {
        if (tipoIntegracaoCaixaBanco == null) {
            return null;
        }
        return tipoIntegracaoCaixaBanco.getValue();
    }

    @Override
    public TipoIntegracaoCaixaBanco convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return TipoIntegracaoCaixaBanco.fromValue(value);
    }
}
