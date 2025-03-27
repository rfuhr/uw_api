package br.com.ultraworks.erp.api.fiscal.domain.tipocomunicacaonfe;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoComunicacaoNfe {
    ENVIO("1", "Envio"),
    CANCELAMENTO("2", "Cancelamento"),
    INUTILIZACAO("3", "Inutilização"),
    CARTA_CORRECAO("4", "Carta de Correção"),
    CONSULTA_STATUS("5", "Consulta Status NFe");
    
    private String value;
    private String name;

    TipoComunicacaoNfe(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    
    public String getName() {
        return name;
    }

    public static TipoComunicacaoNfe fromValue(String value) {
        for (TipoComunicacaoNfe tipo : TipoComunicacaoNfe.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoComunicacaoNfe.values().length; i++) {
			TipoComunicacaoNfe tipo = TipoComunicacaoNfe.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
