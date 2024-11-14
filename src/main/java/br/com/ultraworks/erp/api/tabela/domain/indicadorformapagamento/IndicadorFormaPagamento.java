package br.com.ultraworks.erp.api.tabela.domain.indicadorformapagamento;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum IndicadorFormaPagamento {
    AVISTA("1", "Pagamento à Vista"),
    APRAZO("2", "Pagamento à Prazo");

    private String value;
    private String name;

    IndicadorFormaPagamento(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    
    public String getName() {
        return name;
    }
    
    public static IndicadorFormaPagamento fromValue(String value) {
        for (IndicadorFormaPagamento tipo : IndicadorFormaPagamento.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < IndicadorFormaPagamento.values().length; i++) {
			IndicadorFormaPagamento formaPagamento = IndicadorFormaPagamento.values()[i];

			list.add(EnumResponse.builder().name(formaPagamento.getName()).value(formaPagamento.getValue()).build());
		}

		return list;
	}
}
