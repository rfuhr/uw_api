package br.com.ultraworks.erp.api.fiscal.domain.tipodocumentoreferenciado;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoDocumentoReferenciado {
    NFENFCE("1", "NFe / NFC-e", "1"),
    NF11A2("2", "NF Modelos 1/1A, 2", "2"),
    NFPRODUTORRURAL("3", "NF de Produtor Rural", "3"),
    CUPOMFISCAL("4", "Cupom Fiscal Referenciado", "4"),
    CTe("5", "CT-e Referenciado", "5");
    
    private String value;
    private String name;
    private String codigoReceita;

    TipoDocumentoReferenciado(String value, String name, String codigoReceita) {
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

    public static TipoDocumentoReferenciado fromValue(String value) {
        for (TipoDocumentoReferenciado tipo : TipoDocumentoReferenciado.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoDocumentoReferenciado.values().length; i++) {
			TipoDocumentoReferenciado tipoOperacao = TipoDocumentoReferenciado.values()[i];

			list.add(EnumResponse.builder().name(tipoOperacao.getName()).value(tipoOperacao.getValue()).build());
		}

		return list;
	}
}
