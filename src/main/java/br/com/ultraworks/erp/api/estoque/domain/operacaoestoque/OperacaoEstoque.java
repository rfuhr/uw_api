package br.com.ultraworks.erp.api.estoque.domain.operacaoestoque;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum OperacaoEstoque {
    SOMA("1", "SOMA", "C"),
    SUBTRAI("2", "SUBTRAI", "D"),
    NAO_OPERA("3", "NÃO OPERA", "N");
    
    private String value;
    private String name;
    private String creditoDebito;

    OperacaoEstoque(String value, String name, String creditoDebito) {
        this.value = value;
        this.name = name;
        this.creditoDebito = creditoDebito;
    }

    public String getValue() {
        return value;
    }
    
    public String getName() {
        return name;
    }
    
    public String getCreditoDebito() {
    	return creditoDebito;
    }

    public static OperacaoEstoque fromValue(String value) {
        for (OperacaoEstoque operacao : OperacaoEstoque.values()) {
            if (operacao.getValue().equalsIgnoreCase(value)) {
                return operacao;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < OperacaoEstoque.values().length; i++) {
			OperacaoEstoque operacao = OperacaoEstoque.values()[i];

			list.add(EnumResponse.builder().name(operacao.getName()).value(operacao.getValue()).build());
		}

		return list;
	}
}
