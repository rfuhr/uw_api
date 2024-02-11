package br.com.ultraworks.erp.api.tabela.domain.tipopessoa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoPessoaConverter implements AttributeConverter<TipoPessoa, String> {

    @Override
    public String convertToDatabaseColumn(TipoPessoa tipoPessoa) {
        if (tipoPessoa == null) {
            return null;
        }
        return tipoPessoa.getCodigo();
    }

    @Override
    public TipoPessoa convertToEntityAttribute(String codigo) {
        if (codigo == null) {
            return null;
        }
        return TipoPessoa.fromCodigo(codigo);
    }
}
