package br.com.ultraworks.erp.api.compras.domain.situacaosolicitacaomercadoria;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum SituacaoSolicitacaoMercadoria {
	EM_DIGITACAO("1", "Em Digitação"),
	DIGITADA("2", "Digitada"),
	AGUARDANDO_AUTORIZACAO("3", "Aguardando Autorização"),
	AUTORIZADA("4", "Autorizada"),
	NAO_AUTORIZADA("5", "Não Autorizada"),
	PARCIALMENTE_AUTORIZADA("6", "Parcialmente Autorizada"),
	ABERTA("7", "Aberta"),
	FECHADA("8", "Fechada"),
	CANCELADA("9", "Cancelada");

	private String value;
	private String name;

	SituacaoSolicitacaoMercadoria(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static SituacaoSolicitacaoMercadoria fromValue(String codigo) {
		for (SituacaoSolicitacaoMercadoria tipo : SituacaoSolicitacaoMercadoria.values()) {
			if (tipo.getValue().equalsIgnoreCase(codigo)) {
				return tipo;
			}
		}
		return null;
	}

	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < SituacaoSolicitacaoMercadoria.values().length; i++) {
			SituacaoSolicitacaoMercadoria tipo = SituacaoSolicitacaoMercadoria.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
