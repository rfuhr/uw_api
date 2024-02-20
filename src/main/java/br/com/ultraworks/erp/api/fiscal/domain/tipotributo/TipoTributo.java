package br.com.ultraworks.erp.api.fiscal.domain.tipotributo;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoTributo {
    ICMS("ICMS"),
    PIS("PIS"),
    COFINS("COFINS"),
    IPI("IPI");

    private String value;

    TipoTributo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TipoTributo fromCodigo(String value) {
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

			list.add(EnumResponse.builder().name(tipoTributo.getValue()).value(tipoTributo.getValue()).build());
		}

		return list;
	}
}
