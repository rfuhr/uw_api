package br.com.ultraworks.erp.api.compras.domain.statuscotacaomercadoriaitem;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum StatusCotacaoMercadoriaItem {
	EM_DIGITACAO("1", "Em Digitação"),
	DIGITADA("2", "Digitada"),
	AGUARDANDO_RETORNO("3", "Aguardando Retorno"),
	AGUARDANDO_MAPA("4", "Aguardando Mapa"),
	APROVADO("5", "Aprovado"),
	DESCLASSIFICADO("6", "Desclassificado"),
	CANCELADO("9", "Cancelada");

	private String value;
	private String name;

	StatusCotacaoMercadoriaItem(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static StatusCotacaoMercadoriaItem fromValue(String codigo) {
		for (StatusCotacaoMercadoriaItem tipo : StatusCotacaoMercadoriaItem.values()) {
			if (tipo.getValue().equalsIgnoreCase(codigo)) {
				return tipo;
			}
		}
		return null;
	}

	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < StatusCotacaoMercadoriaItem.values().length; i++) {
			StatusCotacaoMercadoriaItem tipo = StatusCotacaoMercadoriaItem.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
