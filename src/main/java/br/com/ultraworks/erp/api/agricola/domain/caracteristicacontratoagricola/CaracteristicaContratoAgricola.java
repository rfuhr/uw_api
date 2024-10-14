package br.com.ultraworks.erp.api.agricola.domain.caracteristicacontratoagricola;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum CaracteristicaContratoAgricola {
	DEPOSITO_FIXAR("1", "Depósito Fixar"), PRECO_FIXO("2", "Preço Fixo"), DOLAR("3", "Dólar");

	private String value;
	private String name;

	CaracteristicaContratoAgricola(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static CaracteristicaContratoAgricola fromValue(String value) {
		for (CaracteristicaContratoAgricola tipo : CaracteristicaContratoAgricola.values()) {
			if (tipo.getValue().equalsIgnoreCase(value)) {
				return tipo;
			}
		}
		return null;
	}

	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < CaracteristicaContratoAgricola.values().length; i++) {
			CaracteristicaContratoAgricola tipo = CaracteristicaContratoAgricola.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
