package br.com.ultraworks.erp.api.financeiro.domain.tipointegracaocaixabanco;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoIntegracaoCaixaBanco {
	SEM_INTEGRACAO("N", "Sem Integração"), 
	CAIXA("C", "Integração de Caixa"), 
	BANCO("B", "Integração de Banco"); 

	private String value;
	private String name;

	TipoIntegracaoCaixaBanco(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static TipoIntegracaoCaixaBanco fromValue(String value) {
		for (TipoIntegracaoCaixaBanco tipo : TipoIntegracaoCaixaBanco.values()) {
			if (tipo.getValue().equalsIgnoreCase(value)) {
				return tipo;
			}
		}
		return null;
	}

	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoIntegracaoCaixaBanco.values().length; i++) {
			TipoIntegracaoCaixaBanco modalidade = TipoIntegracaoCaixaBanco.values()[i];

			list.add(EnumResponse.builder().name(modalidade.getName()).value(modalidade.getValue()).build());
		}

		return list;
	}
}
