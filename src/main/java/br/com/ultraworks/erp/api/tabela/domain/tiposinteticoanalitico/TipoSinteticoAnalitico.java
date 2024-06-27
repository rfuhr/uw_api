package br.com.ultraworks.erp.api.tabela.domain.tiposinteticoanalitico;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoSinteticoAnalitico {
    SINTETICO("1", "Sintético"),
    ANALITICO("2", "Analítico");

    private String value;
    private String name;

    TipoSinteticoAnalitico(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    
    public String getName() {
    	return name;
    }

    public static TipoSinteticoAnalitico fromValue(String value) {
        for (TipoSinteticoAnalitico tipo : TipoSinteticoAnalitico.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null; // Ou lançar uma exceção se preferir
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoSinteticoAnalitico.values().length; i++) {
			TipoSinteticoAnalitico tipo = TipoSinteticoAnalitico.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
    
}

