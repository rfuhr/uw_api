package br.com.ultraworks.erp.api.tabela.domain.situacaodocumento;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.api.tabela.domain.tipointegracaopagamento.TipoIntegracaoPagamento;
import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum SituacaoDocumento {
    PENDENTE("1", "Pendente"),
    EMDIGITACAO("2", "Em Digitação"),
    CONCLUIDO("3", "Concluído"),
    AGUARDANDOAPROVACAO("4", "Aguardando Aprovação"),
    AUTORIZADO("5", "Autorizado"),
    INUTILIZADO("6", "Inutilizado"),
    REJEITADO("7", "Rejeitado"),
    DENEGADO("8", "Denegado"),
    CANCELADO("9", "Cancelado"),
    AGUARDANDOENVIO("10","Aguardando Envio");

    private String value;
    private String name;

    SituacaoDocumento(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    
    public String getName() {
    	return name;
    }

    public static SituacaoDocumento fromValue(String value) {
        for (SituacaoDocumento tipo : SituacaoDocumento.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null; 
    }
    
    public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < SituacaoDocumento.values().length; i++) {
			SituacaoDocumento tipo = SituacaoDocumento.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}

