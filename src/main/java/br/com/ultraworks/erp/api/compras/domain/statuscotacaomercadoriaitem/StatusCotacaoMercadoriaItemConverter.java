package br.com.ultraworks.erp.api.compras.domain.statuscotacaomercadoriaitem;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusCotacaoMercadoriaItemConverter implements AttributeConverter<StatusCotacaoMercadoriaItem, String> {

    @Override
    public String convertToDatabaseColumn(StatusCotacaoMercadoriaItem situacao) {
        if (situacao == null) {
            return null;
        }
        return situacao.getValue();
    }

    @Override
    public StatusCotacaoMercadoriaItem convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return StatusCotacaoMercadoriaItem.fromValue(value);
    }
}
