package br.com.ultraworks.erp.api.configuracao.domain.tipoemissao;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoEmissao {
	EMISSAO_NORMAL("1", "Emissão normal (não em contingência)"),
    CONTIGENCIA_FSIA("2", "Contingência FS-IA, com impressão do DANFE em formulário de segurança"),
    CONTIGENCIA_SCAN("3", "Contingência SCAN (Sistema de Contingência do Ambiente Nacional)"),
    CONTIGENCIA_DPEC("4", "Contingência DPEC (Declaração Prévia da Emissão em Contingência)"),
    CONTIGENCIA_FSDA("5", "Contingência FS-DA, com impressão do DANFE em formulário de segurança"),
    CONTIGENCIA_SVCAN("6", "Contingência SVC-AN (SEFAZ Virtual de Contingência do AN)"),
    CONTIGENCIA_SVCRS("7", "Contingência SVC-RS (SEFAZ Virtual de Contingência do RS)");

    private String value;
    private String name;

    TipoEmissao(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
    	return name;
    }
    
    public static TipoEmissao fromValue(String value) {
        for (TipoEmissao tipo : TipoEmissao.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoEmissao.values().length; i++) {
			TipoEmissao tipoEmissao = TipoEmissao.values()[i];

			list.add(EnumResponse.builder().name(tipoEmissao.getName()).value(tipoEmissao.getValue()).build());
		}

		return list;
	}
}
