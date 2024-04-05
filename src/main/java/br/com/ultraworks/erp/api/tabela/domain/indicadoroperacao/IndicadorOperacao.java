package br.com.ultraworks.erp.api.tabela.domain.indicadoroperacao;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum IndicadorOperacao {
    ENTRADA("E", "Entrada", "0"),
    SAIDA("S", "Saída", "1");

    private String value;
    private String name;
    private String codigoReceita;

    IndicadorOperacao(String value, String name, String codigoReceita) {
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

    public static IndicadorOperacao fromValue(String value) {
        for (IndicadorOperacao tipo : IndicadorOperacao.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < IndicadorOperacao.values().length; i++) {
			IndicadorOperacao tipoOperacao = IndicadorOperacao.values()[i];

			list.add(EnumResponse.builder().name(tipoOperacao.getName()).value(tipoOperacao.getValue()).build());
		}

		return list;
	}
}
