package br.com.ultraworks.erp.api.fiscal.domain.tipocalculo;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoCalculo {
    VALOR("V", "Valor"),
    PERCENTUAL("P", "Percentual");

    private String value;
    private String name;

    TipoCalculo(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    
    public String getName() {
		return name;
	}

	public static TipoCalculo fromValue(String value) {
        for (TipoCalculo tipo : TipoCalculo.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoCalculo.values().length; i++) {
			TipoCalculo tipoCalculo = TipoCalculo.values()[i];

			list.add(EnumResponse.builder().name(tipoCalculo.getName()).value(tipoCalculo.getValue()).build());
		}

		return list;
	}
}
