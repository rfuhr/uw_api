package br.com.ultraworks.erp.api.tabela.domain.situacaodocumento;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SituacaoDocumentoConverter implements AttributeConverter<SituacaoDocumento, String> {

    @Override
    public String convertToDatabaseColumn(SituacaoDocumento tipoPessoa) {
        if (tipoPessoa == null) {
            return null;
        }
        return tipoPessoa.getValue();
    }

    @Override
    public SituacaoDocumento convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return SituacaoDocumento.fromValue(value);
    }
}
