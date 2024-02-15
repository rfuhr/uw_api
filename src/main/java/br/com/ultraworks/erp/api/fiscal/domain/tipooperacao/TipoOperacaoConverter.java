package br.com.ultraworks.erp.api.fiscal.domain.tipooperacao;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoOperacaoConverter implements AttributeConverter<TipoOperacao, String> {

    @Override
    public String convertToDatabaseColumn(TipoOperacao TipoOperacao) {
        if (TipoOperacao == null) {
            return null;
        }
        return TipoOperacao.getCodigo();
    }

    @Override
    public TipoOperacao convertToEntityAttribute(String codigo) {
        if (codigo == null) {
            return null;
        }
        return TipoOperacao.fromCodigo(codigo);
    }
}
