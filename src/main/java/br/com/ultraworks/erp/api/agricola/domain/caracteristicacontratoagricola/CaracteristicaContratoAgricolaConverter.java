package br.com.ultraworks.erp.api.agricola.domain.caracteristicacontratoagricola;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CaracteristicaContratoAgricolaConverter implements AttributeConverter<CaracteristicaContratoAgricola, String> {

	@Override
	public String convertToDatabaseColumn(CaracteristicaContratoAgricola origem) {
		if (origem == null) {
			return null;
		}
		return origem.getValue();
	}

	@Override
	public CaracteristicaContratoAgricola convertToEntityAttribute(String value) {
		if (value == null) {
			return null;
		}
		return CaracteristicaContratoAgricola.fromValue(value);
	}
}
