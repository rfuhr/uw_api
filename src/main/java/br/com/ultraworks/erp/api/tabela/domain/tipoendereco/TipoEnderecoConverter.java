package br.com.ultraworks.erp.api.tabela.domain.tipoendereco;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoEnderecoConverter implements AttributeConverter<TipoEndereco, String> {

    @Override
    public String convertToDatabaseColumn(TipoEndereco tipoEndereco) {
        if (tipoEndereco == null) {
            return null;
        }
        return tipoEndereco.getValue();
    }

    @Override
    public TipoEndereco convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return TipoEndereco.fromValue(value);
    }
}
