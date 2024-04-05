package br.com.ultraworks.erp.api.fiscal.domain.tipointermediador;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoIntermediador {
    SEMINTERMEDIADOR("0", "Operação sem intermediador (em site ou plataforma própria", "0"),
    COMINTERMEDIADOR("1", "Operação com intermediador (em site ou plataforma de terceiros", "1");
    
    private String value;
    private String name;
    private String codigoReceita;

    TipoIntermediador(String value, String name, String codigoReceita) {
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

    public static TipoIntermediador fromValue(String value) {
        for (TipoIntermediador tipo : TipoIntermediador.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoIntermediador.values().length; i++) {
			TipoIntermediador tipoOperacao = TipoIntermediador.values()[i];

			list.add(EnumResponse.builder().name(tipoOperacao.getName()).value(tipoOperacao.getValue()).build());
		}

		return list;
	}
}
