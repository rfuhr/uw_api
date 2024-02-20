package br.com.ultraworks.erp.api.tabela.domain.grandezaMedida;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum GrandezaMedida {
	AREA("Área", "Área"), COMPRIMENTO("Comprimento", "Comprimento"), VOLUME("Volume", "Volume"), PESO("Peso", "Peso"),
	EMBALAGEM("Embalagem", "Embalagem"), CONSUMO("Consumo", "Consumo");

	private String value;
	private String name;

	GrandezaMedida(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static GrandezaMedida fromValue(String value) {
		for (GrandezaMedida tipo : GrandezaMedida.values()) {
			if (tipo.getValue().equalsIgnoreCase(value)) {
				return tipo;
			}
		}
		return null;
	}

	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < GrandezaMedida.values().length; i++) {
			GrandezaMedida grandezaMedida = GrandezaMedida.values()[i];

			list.add(EnumResponse.builder().name(grandezaMedida.getName()).value(grandezaMedida.getValue()).build());
		}

		return list;
	}
}
