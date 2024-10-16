package br.com.ultraworks.erp.api.fiscal.domain.tipointegracao;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoIntegracao {
	ESTOQUE("1", "Estoque"), 
	FINANCEIRO("2", "Financeiro"); 

	private String value;
	private String name;

	TipoIntegracao(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static TipoIntegracao fromValue(String value) {
		for (TipoIntegracao tipo : TipoIntegracao.values()) {
			if (tipo.getValue().equalsIgnoreCase(value)) {
				return tipo;
			}
		}
		return null;
	}

	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoIntegracao.values().length; i++) {
			TipoIntegracao tipo = TipoIntegracao.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
