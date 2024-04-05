package br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculost;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum ModalidadeBaseCalculoSt {
	PRECO_TABELADO_SUGERIDO("0", "Preço tabelado ou máximo sugerido"), 
	LISTA_NEGATIVA("1", "Lista Negativa (valor)"), 
	LISTA_POSITIVA("2", "Lista Positiva (valor)"),
	LISTA_NEUTRA("3", "Lista Neutra (valor)"),
	MVA("4", "Margem Valor Agregado (%)"),
	PAUTA("5", "Pauta (valor)");
	

	private String value;
	private String name;

	ModalidadeBaseCalculoSt(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static ModalidadeBaseCalculoSt fromValue(String value) {
		for (ModalidadeBaseCalculoSt tipo : ModalidadeBaseCalculoSt.values()) {
			if (tipo.getValue().equalsIgnoreCase(value)) {
				return tipo;
			}
		}
		return null;
	}

	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < ModalidadeBaseCalculoSt.values().length; i++) {
			ModalidadeBaseCalculoSt modalidade = ModalidadeBaseCalculoSt.values()[i];

			list.add(EnumResponse.builder().name(modalidade.getName()).value(modalidade.getValue()).build());
		}

		return list;
	}
}
