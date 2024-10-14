package br.com.ultraworks.erp.api.agricola.domain.basecalculoagricola;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum BaseCalculoAgricola {
	PESO_BRUTO("1", "Peso Bruto"), 
	TARA("2", "Tara"),
	PESO_LIQUIDO("3", "Peso Líquido"),
	SEM_BASE("99", "Sem Base");

	private String value;
	private String name;

	BaseCalculoAgricola(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static BaseCalculoAgricola fromValue(String value) {
		for (BaseCalculoAgricola tipo : BaseCalculoAgricola.values()) {
			if (tipo.getValue().equalsIgnoreCase(value)) {
				return tipo;
			}
		}
		return null;
	}

	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < BaseCalculoAgricola.values().length; i++) {
			BaseCalculoAgricola tipo = BaseCalculoAgricola.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
