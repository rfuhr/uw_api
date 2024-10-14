package br.com.ultraworks.erp.api.agricola.domain.tipotaxaagricola;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoTaxaAgricolaConverter implements AttributeConverter<TipoTaxaAgricola, String> {

	@Override
	public String convertToDatabaseColumn(TipoTaxaAgricola origem) {
		if (origem == null) {
			return null;
		}
		return origem.getValue();
	}

	@Override
	public TipoTaxaAgricola convertToEntityAttribute(String value) {
		if (value == null) {
			return null;
		}
		return TipoTaxaAgricola.fromValue(value);
	}
}
