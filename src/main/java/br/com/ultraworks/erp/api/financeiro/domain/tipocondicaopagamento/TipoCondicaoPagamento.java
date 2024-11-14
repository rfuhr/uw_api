package br.com.ultraworks.erp.api.financeiro.domain.tipocondicaopagamento;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoCondicaoPagamento {
	AVISTA("1", "Á Vista"), 
	DIA_FIXO("2", "Dia Fixo"), 
	INTERVALO_DIAS("3", "Intervalo de Dias"),
	DIVISAO_DIAS("4", "Divisão de Dias"),
	COMPOSICAO("5", "Composição"),
	PERSONALIZADA("6", "Personalizada");

	private String value;
	private String name;

	TipoCondicaoPagamento(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static TipoCondicaoPagamento fromValue(String value) {
		for (TipoCondicaoPagamento tipo : TipoCondicaoPagamento.values()) {
			if (tipo.getValue().equalsIgnoreCase(value)) {
				return tipo;
			}
		}
		return null;
	}

	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoCondicaoPagamento.values().length; i++) {
			TipoCondicaoPagamento tipo = TipoCondicaoPagamento.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
