package br.com.ultraworks.erp.api.configuracao.domain.tipocertificado;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoCertificado {
    A1("A1"),
    A3("A3");

    private String value;

    TipoCertificado(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TipoCertificado fromValue(String value) {
        for (TipoCertificado tipo : TipoCertificado.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoCertificado.values().length; i++) {
			TipoCertificado tipoCalculo = TipoCertificado.values()[i];

			list.add(EnumResponse.builder().name(tipoCalculo.getValue()).value(tipoCalculo.getValue()).build());
		}

		return list;
	}
}
