package br.com.ultraworks.erp.api.estoque.domain.tipodocumentoestoque;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoDocumentoEstoque {
    NFE("1", "NFe"),
    DOCUMENTO_INTERNO("2", "Documento Interno");
    
    private String value;
    private String name;

    TipoDocumentoEstoque(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    
    public String getName() {
        return name;
    }

    public static TipoDocumentoEstoque fromValue(String value) {
        for (TipoDocumentoEstoque tipo : TipoDocumentoEstoque.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoDocumentoEstoque.values().length; i++) {
			TipoDocumentoEstoque tipo = TipoDocumentoEstoque.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
