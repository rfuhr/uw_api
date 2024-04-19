package br.com.ultraworks.erp.api.tabela.domain.tipointegracaopagamento;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.api.tabela.domain.indicadoroperacao.IndicadorOperacao;
import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoIntegracaoPagamento {
	AUTOMACAO("1", "Automação da Empresa"),
	POS("2", "Equipamento POS");

	private String value;
	private String name;

	TipoIntegracaoPagamento(String value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public static TipoIntegracaoPagamento fromValue(String value) {
		for (TipoIntegracaoPagamento tipo : TipoIntegracaoPagamento.values()) {
			if (tipo.getValue().equalsIgnoreCase(value)) {
				return tipo;
			}
		}
		return null; 
	}
	
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoIntegracaoPagamento.values().length; i++) {
			TipoIntegracaoPagamento tipo = TipoIntegracaoPagamento.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
