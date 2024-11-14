package br.com.ultraworks.erp.api.compras.domain.situacaocotacaomercadoriaparceiro;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum SituacaoCotacaoMercadoriaParceiro {
	EM_DIGITACAO("1", "Em Digitação"),
	DIGITADA("2", "Digitada"),
	AGUARDANDO_RETORNO("3", "Aguardando Retorno"),
	RETORNO_PARCIAL("4", "Retorno Parcial"),
	AGUARDANDO_MAPA("5", "Aguardando Mapa"),
	APROVADA_TOTAL("6", "Aprovado Total"),
	APROVADA_PARCIALMENTE("7", "Aprovado Parcialmente"),
	DESCLASSIFICADA("8", "Desclassificada"),
	CANCELADA("9", "Cancelada");

	private String value;
	private String name;

	SituacaoCotacaoMercadoriaParceiro(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static SituacaoCotacaoMercadoriaParceiro fromValue(String codigo) {
		for (SituacaoCotacaoMercadoriaParceiro tipo : SituacaoCotacaoMercadoriaParceiro.values()) {
			if (tipo.getValue().equalsIgnoreCase(codigo)) {
				return tipo;
			}
		}
		return null;
	}

	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < SituacaoCotacaoMercadoriaParceiro.values().length; i++) {
			SituacaoCotacaoMercadoriaParceiro tipo = SituacaoCotacaoMercadoriaParceiro.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
