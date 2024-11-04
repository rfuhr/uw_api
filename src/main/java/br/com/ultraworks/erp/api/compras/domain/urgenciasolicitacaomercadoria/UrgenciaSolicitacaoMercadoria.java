package br.com.ultraworks.erp.api.compras.domain.urgenciasolicitacaomercadoria;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum UrgenciaSolicitacaoMercadoria {
	BAIXA("1", "Baixa"), NORMAL("2", "Normal"), MEDIA("3", "Média"), ALTA("4", "Alta");

	private String value;
	private String name;

	UrgenciaSolicitacaoMercadoria(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static UrgenciaSolicitacaoMercadoria fromValue(String codigo) {
		for (UrgenciaSolicitacaoMercadoria tipo : UrgenciaSolicitacaoMercadoria.values()) {
			if (tipo.getValue().equalsIgnoreCase(codigo)) {
				return tipo;
			}
		}
		return null;
	}

	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < UrgenciaSolicitacaoMercadoria.values().length; i++) {
			UrgenciaSolicitacaoMercadoria tipo = UrgenciaSolicitacaoMercadoria.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
