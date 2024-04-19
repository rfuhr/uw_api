package br.com.ultraworks.erp.api.fiscal.domain.modalidadefrete;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum ModalidadeFrete {
	CIF("0", "Contratação do Frete por conta do Remetente (CIF)"), 
	FOB("1", "Contratação do Frete por conta do Destinatário (FOB)"), 
	TERCEIRO("2", "Contratação do Frete por conta de Terceiros"),
	PROPRIO_REMETENTE("3", "Transporte Próprio por conta do Remetente"),
	PROPRIO_DESTINATARIO("4", "Transporte Próprio por conta do Destinatário"),
	SEM_FRETE("9", "Sem Ocorrência de Transporte"); 

	private String value;
	private String name;

	ModalidadeFrete(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static ModalidadeFrete fromValue(String value) {
		for (ModalidadeFrete tipo : ModalidadeFrete.values()) {
			if (tipo.getValue().equalsIgnoreCase(value)) {
				return tipo;
			}
		}
		return null;
	}

	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < ModalidadeFrete.values().length; i++) {
			ModalidadeFrete modalidade = ModalidadeFrete.values()[i];

			list.add(EnumResponse.builder().name(modalidade.getName()).value(modalidade.getValue()).build());
		}

		return list;
	}
}
