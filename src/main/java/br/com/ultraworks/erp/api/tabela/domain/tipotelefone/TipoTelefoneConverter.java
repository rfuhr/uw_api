package br.com.ultraworks.erp.api.tabela.domain.tipotelefone;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

//@Converter(autoApply = true)
public class TipoTelefoneConverter implements AttributeConverter<TipoTelefone, String> {

	@Override
	public String convertToDatabaseColumn(TipoTelefone tipoTelefone) {
		if (tipoTelefone == null) {
			return null;
		}
		return tipoTelefone.getValue();
	}

	@Override
	public TipoTelefone convertToEntityAttribute(String value) {
		if (value == null) {
			return null;
		}
		return TipoTelefone.fromValue(value);
	}
}
