package br.com.ultraworks.erp.api.fiscal.domain.entradasaida;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum EntradaSaida {
    ENTRADA("Entrada"),
    SAIDA("Saída)");

    private String value;

    EntradaSaida(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EntradaSaida fromCodigo(String value) {
        for (EntradaSaida tipo : EntradaSaida.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null; 
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < EntradaSaida.values().length; i++) {
			EntradaSaida entradaSaida = EntradaSaida.values()[i];

			list.add(EnumResponse.builder().name(entradaSaida.getValue()).value(entradaSaida.getValue()).build());
		}

		return list;
	}
}
