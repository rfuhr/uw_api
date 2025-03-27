package br.com.ultraworks.erp.api.fiscal.domain.tiponfe;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoNfe {
    PROPRIA("1", "Própria"),
    TERCEIRO("2", "Terceiro");
    
    private String value;
    private String name;

    TipoNfe(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    
    public String getName() {
        return name;
    }

    public static TipoNfe fromValue(String value) {
        for (TipoNfe tipo : TipoNfe.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoNfe.values().length; i++) {
			TipoNfe tipo = TipoNfe.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
