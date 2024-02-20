package br.com.ultraworks.erp.api.fiscal.domain.tipooperacao;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoOperacao {
    ESTADUAL("Estadual"),
    EXTERIOR("Exterior"),
	INTERESTADUAL("Interestadual");

    private String value;

    TipoOperacao(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TipoOperacao fromCodigo(String codigo) {
        for (TipoOperacao tipo : TipoOperacao.values()) {
            if (tipo.getValue().equalsIgnoreCase(codigo)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoOperacao.values().length; i++) {
			TipoOperacao tipoOperacao = TipoOperacao.values()[i];

			list.add(EnumResponse.builder().name(tipoOperacao.getValue()).value(tipoOperacao.getValue()).build());
		}

		return list;
	}
}
