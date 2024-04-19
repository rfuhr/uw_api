package br.com.ultraworks.erp.api.fiscal.domain.tipotransporte;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoTransporte {
    VEICULO("1", "Veículo"),
    VAGAO("2", "Vagão"),
    BALSA("3", "Balsa");

    private String value;
    private String name;

    TipoTransporte(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    
    public String getName() {
		return name;
	}

	public static TipoTransporte fromValue(String value) {
        for (TipoTransporte tipo : TipoTransporte.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoTransporte.values().length; i++) {
			TipoTransporte tipoTransporte = TipoTransporte.values()[i];

			list.add(EnumResponse.builder().name(tipoTransporte.getName()).value(tipoTransporte.getValue()).build());
		}

		return list;
	}
}
