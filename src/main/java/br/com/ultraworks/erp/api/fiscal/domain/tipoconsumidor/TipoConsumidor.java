package br.com.ultraworks.erp.api.fiscal.domain.tipoconsumidor;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoConsumidor {
    NORMAL("0", "Normal", "0"),
    FINAL("1", "Consumidor Final", "1");
    
    private String value;
    private String name;
    private String codigoReceita;

    TipoConsumidor(String value, String name, String codigoReceita) {
        this.value = value;
        this.name = name;
        this.codigoReceita = codigoReceita;
    }

    public String getValue() {
        return value;
    }
    
    public String getName() {
        return name;
    }
    
    public String getCodigoReceita() {
    	return codigoReceita;
    }

    public static TipoConsumidor fromValue(String value) {
        for (TipoConsumidor tipo : TipoConsumidor.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoConsumidor.values().length; i++) {
			TipoConsumidor tipoOperacao = TipoConsumidor.values()[i];

			list.add(EnumResponse.builder().name(tipoOperacao.getName()).value(tipoOperacao.getValue()).build());
		}

		return list;
	}
}
