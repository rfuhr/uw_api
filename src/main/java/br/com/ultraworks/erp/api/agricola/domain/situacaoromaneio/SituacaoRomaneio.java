package br.com.ultraworks.erp.api.agricola.domain.situacaoromaneio;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum SituacaoRomaneio {
	PENDENTE("1", "Pendente"), ABERTO("2", "Aberto"), FINALIZADO("3", "Finalizado"), CANCELADO("9", "Cancelado");

	private String value;
	private String name;

	SituacaoRomaneio(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static SituacaoRomaneio fromValue(String value) {
		for (SituacaoRomaneio tipo : SituacaoRomaneio.values()) {
			if (tipo.getValue().equalsIgnoreCase(value)) {
				return tipo;
			}
		}
		return null;
	}

	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < SituacaoRomaneio.values().length; i++) {
			SituacaoRomaneio tipo = SituacaoRomaneio.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
