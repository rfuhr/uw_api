package br.com.ultraworks.erp.api.agricola.domain.tipotaxaagricola;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoTaxaAgricola {
	PERCENTUAL("1", "Percentual"), INDICE("2", "Indíce"), VALOR_ABSOLUTO("3", "Valor Absoluto");

	private String value;
	private String name;

	TipoTaxaAgricola(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static TipoTaxaAgricola fromValue(String value) {
		for (TipoTaxaAgricola tipo : TipoTaxaAgricola.values()) {
			if (tipo.getValue().equalsIgnoreCase(value)) {
				return tipo;
			}
		}
		return null;
	}

	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoTaxaAgricola.values().length; i++) {
			TipoTaxaAgricola tipo = TipoTaxaAgricola.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
