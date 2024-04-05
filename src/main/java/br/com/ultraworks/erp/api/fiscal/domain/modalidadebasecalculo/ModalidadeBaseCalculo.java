package br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculo;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum ModalidadeBaseCalculo {
	MVA("0", "Margem Valor Agregado"), PAUTA("1", "Pauta (Valor)"), PRECO("2", "Preço Tabelado"),
	VALOR("3", "Valor da Operação");

	private String value;
	private String name;

	ModalidadeBaseCalculo(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static ModalidadeBaseCalculo fromValue(String value) {
		for (ModalidadeBaseCalculo tipo : ModalidadeBaseCalculo.values()) {
			if (tipo.getValue().equalsIgnoreCase(value)) {
				return tipo;
			}
		}
		return null;
	}

	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < ModalidadeBaseCalculo.values().length; i++) {
			ModalidadeBaseCalculo modalidade = ModalidadeBaseCalculo.values()[i];

			list.add(EnumResponse.builder().name(modalidade.getName()).value(modalidade.getValue()).build());
		}

		return list;
	}
}
