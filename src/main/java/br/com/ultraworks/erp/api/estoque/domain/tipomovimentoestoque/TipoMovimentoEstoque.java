package br.com.ultraworks.erp.api.estoque.domain.tipomovimentoestoque;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoMovimentoEstoque {
    SALDO_INICIAL("1", "Saldo Inicial"),
    INCLUSAO("2", "Inclusão"),
    BAIXA("3", "Baixa"),
    CANCELAMENTO("4", "Cancelamento");
    
    private String value;
    private String name;

    TipoMovimentoEstoque(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    
    public String getName() {
        return name;
    }

    public static TipoMovimentoEstoque fromValue(String value) {
        for (TipoMovimentoEstoque tipo : TipoMovimentoEstoque.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoMovimentoEstoque.values().length; i++) {
			TipoMovimentoEstoque tipo = TipoMovimentoEstoque.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
