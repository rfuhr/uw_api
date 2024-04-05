package br.com.ultraworks.erp.api.fiscal.domain.finalidadenfe;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum FinalidadeNfe {
    NORMAL("1", "NF-e Normal", "1"),
    COMPLEMENTAR("2", "NF-e Complementar", "2"),
    AJUSTE("3", "NF-e de  Ajuste", "3"),
    DEVOLUCAO("4", "Devolução de Mercadoria", "4");
    
    private String value;
    private String name;
    private String codigoReceita;

    FinalidadeNfe(String value, String name, String codigoReceita) {
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

    public static FinalidadeNfe fromValue(String value) {
        for (FinalidadeNfe tipo : FinalidadeNfe.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < FinalidadeNfe.values().length; i++) {
			FinalidadeNfe tipoOperacao = FinalidadeNfe.values()[i];

			list.add(EnumResponse.builder().name(tipoOperacao.getName()).value(tipoOperacao.getValue()).build());
		}

		return list;
	}
}
