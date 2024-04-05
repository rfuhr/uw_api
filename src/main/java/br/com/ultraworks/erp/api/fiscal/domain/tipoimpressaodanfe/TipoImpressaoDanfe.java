package br.com.ultraworks.erp.api.fiscal.domain.tipoimpressaodanfe;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoImpressaoDanfe {
    SEMDANFE("0", "Sem Danfe", "0"),
    DANFENORMALRETRATO("1", "Danfe Normal - Retrato", "1"),
    DANFENORMALPAISAGEM("2", "Danfe Normal - Paisagem", "2"),
    DANFESIMPLIFICADO("3", "Danfe Simplificado", "3"),
    DANFENFCe("4", "Danfe NFCe", "4"),
    DANFENFCeMSGELETRONICA("5", "Danfe NFCe Mensagem Eletrônica", "5");
    
    private String value;
    private String name;
    private String codigoReceita;

    TipoImpressaoDanfe(String value, String name, String codigoReceita) {
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

    public static TipoImpressaoDanfe fromValue(String value) {
        for (TipoImpressaoDanfe tipo : TipoImpressaoDanfe.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoImpressaoDanfe.values().length; i++) {
			TipoImpressaoDanfe tipoOperacao = TipoImpressaoDanfe.values()[i];

			list.add(EnumResponse.builder().name(tipoOperacao.getName()).value(tipoOperacao.getValue()).build());
		}

		return list;
	}
}
