package br.com.ultraworks.erp.api.tabela.domain.tipoautorizacao;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoAutorizacao {
	SOLICITACAO_MERCADORIA("1", "Solicitação de Mercadoria");

	private String value;
	private String name;

	TipoAutorizacao(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static TipoAutorizacao fromValue(String codigo) {
		for (TipoAutorizacao tipo : TipoAutorizacao.values()) {
			if (tipo.getValue().equalsIgnoreCase(codigo)) {
				return tipo;
			}
		}
		return null;
	}

	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoAutorizacao.values().length; i++) {
			TipoAutorizacao tipo = TipoAutorizacao.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
