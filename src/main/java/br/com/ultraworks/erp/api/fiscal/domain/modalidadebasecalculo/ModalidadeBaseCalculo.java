package br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculo;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum ModalidadeBaseCalculo {
    MVA("Margem Valor Agregado"),
    PAUTA("Pauta (Valor)"),
    PRECO("Preço Tabelado"),
    VALOR("Valor da Operação");

    private String value;

    ModalidadeBaseCalculo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ModalidadeBaseCalculo fromCodigo(String codigo) {
        for (ModalidadeBaseCalculo tipo : ModalidadeBaseCalculo.values()) {
            if (tipo.getValue().equalsIgnoreCase(codigo)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < ModalidadeBaseCalculo.values().length; i++) {
			ModalidadeBaseCalculo modalidade = ModalidadeBaseCalculo.values()[i];

			list.add(EnumResponse.builder().name(modalidade.getValue()).value(modalidade.getValue()).build());
		}

		return list;
	}
}
