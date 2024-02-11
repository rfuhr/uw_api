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
        return tipoEndereco.getCodigo();
    }

    @Override
    public TipoEndereco convertToEntityAttribute(String codigo) {
        if (codigo == null) {
            return null;
        }
        return TipoEndereco.fromCodigo(codigo);
    }
}
