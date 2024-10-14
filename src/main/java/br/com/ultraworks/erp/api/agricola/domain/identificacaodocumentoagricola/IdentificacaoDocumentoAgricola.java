package br.com.ultraworks.erp.api.agricola.domain.identificacaodocumentoagricola;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum IdentificacaoDocumentoAgricola {
	REGRAGERAL("0", "Regra Geral"), ROMANEIO("1", "Romaneio"), FIXACAO("2", "Fixação"), CONTRATO("3", "Contrato"), PESAGEM("3", "Pesagem");

	private String value;
	private String name;

	IdentificacaoDocumentoAgricola(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static IdentificacaoDocumentoAgricola fromValue(String value) {
		for (IdentificacaoDocumentoAgricola tipo : IdentificacaoDocumentoAgricola.values()) {
			if (tipo.getValue().equalsIgnoreCase(value)) {
				return tipo;
			}
		}
		return null;
	}

	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < IdentificacaoDocumentoAgricola.values().length; i++) {
			IdentificacaoDocumentoAgricola tipo = IdentificacaoDocumentoAgricola.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
