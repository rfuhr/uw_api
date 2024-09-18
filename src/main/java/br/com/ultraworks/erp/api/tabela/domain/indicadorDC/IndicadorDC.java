package br.com.ultraworks.erp.api.tabela.domain.indicadorDC;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.api.tabela.domain.indicadoroperacao.IndicadorOperacao;
import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum IndicadorDC {
    DEBITO("D", "Débito"),
    CRÉDITO("C", "Crédito");

    private String value;
    private String name;

    IndicadorDC(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    
    public String getName() {
    	return name;
    }

    public static IndicadorDC fromValue(String value) {
        for (IndicadorDC tipo : IndicadorDC.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null; 
    }
    
    public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < IndicadorDC.values().length; i++) {
			IndicadorDC indicador = IndicadorDC.values()[i];

			list.add(EnumResponse.builder().name(indicador.getName()).value(indicador.getValue()).build());
		}

		return list;
	}
}

