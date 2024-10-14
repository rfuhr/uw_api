package br.com.ultraworks.erp.api.agricola.domain.origemromaneio;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrigemRomaneioConverter implements AttributeConverter<OrigemRomaneio, String> {

	@Override
	public String convertToDatabaseColumn(OrigemRomaneio origem) {
		if (origem == null) {
			return null;
		}
		return origem.getValue();
	}

	@Override
	public OrigemRomaneio convertToEntityAttribute(String value) {
		if (value == null) {
			return null;
		}
		return OrigemRomaneio.fromValue(value);
	}
}
