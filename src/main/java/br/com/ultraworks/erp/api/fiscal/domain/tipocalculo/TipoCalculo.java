package br.com.ultraworks.erp.api.fiscal.domain.tipocalculo;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoCalculo {
    PERCENTUAL("Percentual"),
    VALOR("Valor");

    private String value;

    TipoCalculo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TipoCalculo fromCodigo(String codigo) {
        for (TipoCalculo tipo : TipoCalculo.values()) {
            if (tipo.getValue().equalsIgnoreCase(codigo)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoCalculo.values().length; i++) {
			TipoCalculo tipoCalculo = TipoCalculo.values()[i];

			list.add(EnumResponse.builder().name(tipoCalculo.getValue()).value(tipoCalculo.getValue()).build());
		}

		return list;
	}
}
