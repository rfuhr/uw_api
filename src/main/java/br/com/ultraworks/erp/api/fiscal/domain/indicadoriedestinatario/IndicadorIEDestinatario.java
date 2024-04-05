package br.com.ultraworks.erp.api.fiscal.domain.indicadoriedestinatario;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum IndicadorIEDestinatario {
    CONTRIBUINTEICMS("1", "Contribuinte ICMS", "1"),
    CONTRIBUINTEISENTO("2", "Contribuinte isento de inscrição", "2"),
    NAOCONTRIBUINTE("9", "Não Contribuinte", "9");
    
    private String value;
    private String name;
    private String codigoReceita;

    IndicadorIEDestinatario(String value, String name, String codigoReceita) {
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

    public static IndicadorIEDestinatario fromValue(String value) {
        for (IndicadorIEDestinatario tipo : IndicadorIEDestinatario.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < IndicadorIEDestinatario.values().length; i++) {
			IndicadorIEDestinatario tipoOperacao = IndicadorIEDestinatario.values()[i];

			list.add(EnumResponse.builder().name(tipoOperacao.getName()).value(tipoOperacao.getValue()).build());
		}

		return list;
	}
}
