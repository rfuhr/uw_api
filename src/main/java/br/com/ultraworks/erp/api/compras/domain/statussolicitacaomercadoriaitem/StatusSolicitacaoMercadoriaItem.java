package br.com.ultraworks.erp.api.compras.domain.statussolicitacaomercadoriaitem;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum StatusSolicitacaoMercadoriaItem {
	EM_DIGITACAO("1", "Em Digitação"),
	DIGITADA("2", "Digitada"),
	AGUARDANDO_AUTORIZACAO("3", "Aguardando Autorização"),
	AUTORIZADA("4", "Autorizada"),
	NAO_AUTORIZADA("5", "Não Autorizada"),
	LIBERADA("6", "Liberada"),
	COTADA("7", "Cotada"),
	CANCELADA("9", "Cancelada");

	private String value;
	private String name;

	StatusSolicitacaoMercadoriaItem(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static StatusSolicitacaoMercadoriaItem fromValue(String codigo) {
		for (StatusSolicitacaoMercadoriaItem tipo : StatusSolicitacaoMercadoriaItem.values()) {
			if (tipo.getValue().equalsIgnoreCase(codigo)) {
				return tipo;
			}
		}
		return null;
	}

	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < StatusSolicitacaoMercadoriaItem.values().length; i++) {
			StatusSolicitacaoMercadoriaItem tipo = StatusSolicitacaoMercadoriaItem.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
