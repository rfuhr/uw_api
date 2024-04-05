package br.com.ultraworks.erp.api.fiscal.domain.destinooperacao;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum DestinoOperacao {
    ESTADUAL("1", "Operação Interna", "1"),
    EXTERIOR("3", "Operação Interestadual", "3"),
	INTERESTADUAL("2", "Operação com Exterior", "2");

    private String value;
    private String name;
    private String codigoReceita;

    DestinoOperacao(String value, String name, String codigoReceita) {
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

    public static DestinoOperacao fromValue(String value) {
        for (DestinoOperacao tipo : DestinoOperacao.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < DestinoOperacao.values().length; i++) {
			DestinoOperacao tipoOperacao = DestinoOperacao.values()[i];

			list.add(EnumResponse.builder().name(tipoOperacao.getName()).value(tipoOperacao.getValue()).build());
		}

		return list;
	}
}
