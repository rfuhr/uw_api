package br.com.ultraworks.erp.api.configuracao.domain.tipoambiente;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoAmbiente {
	PRODUCAO("1", "Produção"),
    HOMOLOCACAO("2", "Homologação");

    private String value;
    private String name;

    TipoAmbiente(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
    	return name;
    }
    
    public static TipoAmbiente fromValue(String value) {
        for (TipoAmbiente tipo : TipoAmbiente.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoAmbiente.values().length; i++) {
			TipoAmbiente tipoAmbiente = TipoAmbiente.values()[i];

			list.add(EnumResponse.builder().name(tipoAmbiente.getName()).value(tipoAmbiente.getValue()).build());
		}

		return list;
	}
}
