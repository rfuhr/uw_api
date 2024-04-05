package br.com.ultraworks.erp.api.fiscal.domain.tipotributo;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoTributo {
    ICMS("ICMS", "ICMS"),
    PIS("PIS", "PIS"),
    COFINS("COFINS", "COFINS"),
    IPI("IPI", "IPI");

    private String value;
    private String name;

    TipoTributo(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    
    public String getName() {
    	return name;
    }

    public static TipoTributo fromValue(String value) {
        for (TipoTributo tipo : TipoTributo.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoTributo.values().length; i++) {
			TipoTributo tipoTributo = TipoTributo.values()[i];

			list.add(EnumResponse.builder().name(tipoTributo.getName()).value(tipoTributo.getValue()).build());
		}

		return list;
	}
}
