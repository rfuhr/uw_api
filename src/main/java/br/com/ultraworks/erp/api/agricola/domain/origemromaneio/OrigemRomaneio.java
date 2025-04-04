package br.com.ultraworks.erp.api.agricola.domain.origemromaneio;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum OrigemRomaneio {
	LANCAMENTO("1", "Lançamento"), PESAGEM("2", "Pesagem"), MANUAL("3", "Manual");

	private String value;
	private String name;

	OrigemRomaneio(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static OrigemRomaneio fromValue(String value) {
		for (OrigemRomaneio tipo : OrigemRomaneio.values()) {
			if (tipo.getValue().equalsIgnoreCase(value)) {
				return tipo;
			}
		}
		return null;
	}

	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < OrigemRomaneio.values().length; i++) {
			OrigemRomaneio tipo = OrigemRomaneio.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
