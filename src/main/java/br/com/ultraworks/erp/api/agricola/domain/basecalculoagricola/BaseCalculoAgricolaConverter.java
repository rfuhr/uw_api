package br.com.ultraworks.erp.api.agricola.domain.basecalculoagricola;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BaseCalculoAgricolaConverter implements AttributeConverter<BaseCalculoAgricola, String> {

	@Override
	public String convertToDatabaseColumn(BaseCalculoAgricola base) {
		if (base == null) {
			return null;
		}
		return base.getValue();
	}

	@Override
	public BaseCalculoAgricola convertToEntityAttribute(String value) {
		if (value == null) {
			return null;
		}
		return BaseCalculoAgricola.fromValue(value);
	}
}
