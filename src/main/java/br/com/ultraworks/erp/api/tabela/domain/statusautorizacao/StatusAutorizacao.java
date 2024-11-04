package br.com.ultraworks.erp.api.tabela.domain.statusautorizacao;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum StatusAutorizacao {
	AGUARDANDO_AUTORIZACAO("1", "Aguardando Autorização"), AUTORIZACAO_CONFIRMADA("2", "Autorização Confirmada"),
	AUTORIZACAO_NEGADA("3", "Autorização Negada"), AUTORIZACAO_TRANSFERIDA("4", "Autorização Transferida"),
	AUTORIZACAO_CANCELADA("9", "Autorização Cancelada");

	private String value;
	private String name;

	StatusAutorizacao(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static StatusAutorizacao fromValue(String codigo) {
		for (StatusAutorizacao tipo : StatusAutorizacao.values()) {
			if (tipo.getValue().equalsIgnoreCase(codigo)) {
				return tipo;
			}
		}
		return null;
	}

	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < StatusAutorizacao.values().length; i++) {
			StatusAutorizacao tipo = StatusAutorizacao.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
