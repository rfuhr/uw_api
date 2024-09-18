package br.com.ultraworks.erp.api.agricola.domain.situacaopesagem;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.api.tabela.domain.tipointegracaopagamento.TipoIntegracaoPagamento;
import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum SituacaoPesagem {
    PENDENTE("1", "Aberta"),
    FINALIZADA("2", "Finalizada"),
    CANCELADO("9", "Cancelada");

    private String value;
    private String name;

    SituacaoPesagem(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    
    public String getName() {
    	return name;
    }

    public static SituacaoPesagem fromValue(String value) {
        for (SituacaoPesagem tipo : SituacaoPesagem.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null; 
    }
    
    public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < SituacaoPesagem.values().length; i++) {
			SituacaoPesagem tipo = SituacaoPesagem.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}

