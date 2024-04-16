package br.com.ultraworks.erp.api.fiscal.domain.seloipi;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum SeloIpi {
	cod971001("9710-01", "Produto Nacional", "Verde combinado com marrom"),
	cod971010("9710-10", "Produto Nacional para Exportação - Tipo 1", "Verde Escuro combinado com marrom"),
	cod971011("9710-11", "Produto Nacional para Exportação - Tipo 2", "Verde Escuro combinado com marrom"),
	cod971012("9710-12", "Produto Nacional para Exportação - Tipo 3", "Verde Escuro combinado com marrom"),
	cod861009("8610-09", "Produto Estrangeiro", "Vermelho combinado com azul");
    
    private String value;
    private String name;
    private String cor;

    SeloIpi(String value, String name, String cor) {
        this.value = value;
        this.name = name;
        this.cor = cor;
    }

    public String getValue() {
        return value;
    }
    
    public String getName() {
        return name;
    }
    
    public String getCor() {
    	return cor;
    }

    public static SeloIpi fromValue(String value) {
        for (SeloIpi tipo : SeloIpi.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < SeloIpi.values().length; i++) {
			SeloIpi tipoOperacao = SeloIpi.values()[i];

			list.add(EnumResponse.builder().name(tipoOperacao.getName()).value(tipoOperacao.getValue()).build());
		}

		return list;
	}
}
