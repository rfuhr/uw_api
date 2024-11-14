package br.com.ultraworks.erp.api.compras.domain.situacaocotacaomercadoria;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum SituacaoCotacaoMercadoria {
	EM_DIGITACAO("1", "Em Digitação"),
	DIGITADA("2", "Digitada"),
	AGUARDANDO_RETORNO("3", "Aguardando Retorno"),
	RETORNO_PARCIAL("4", "Retorno Parcial"),
	AGUARDANDO_MAPA("5", "Aguardando Mapa"),
	FINALIZADA("6", "Finalizada"),
	CANCELADA("9", "Cancelada");

	private String value;
	private String name;

	SituacaoCotacaoMercadoria(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static SituacaoCotacaoMercadoria fromValue(String codigo) {
		for (SituacaoCotacaoMercadoria tipo : SituacaoCotacaoMercadoria.values()) {
			if (tipo.getValue().equalsIgnoreCase(codigo)) {
				return tipo;
			}
		}
		return null;
	}

	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < SituacaoCotacaoMercadoria.values().length; i++) {
			SituacaoCotacaoMercadoria tipo = SituacaoCotacaoMercadoria.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
