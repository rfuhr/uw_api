package br.com.ultraworks.erp.api.fiscal.domain.tipopresencacomprador;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoPresencaComprador {
    NAOSEAPLICA("0", "Não se aplica", "0"),
    PRESENCIAL("1", "Operação Presencial", "1"),
    NAOPRESENCIALINTERNET("2", "Operação não presencial, internet", "2"),
    NAOPRESENCIALTELE("3", "Operação não presencial, teleatendimento", "3"),
    NFEENTREGADOMICILIO("4", "NFC-e em operação com entrega domícilio", "4"),
    PRESENCIALFORA("5", "Operação presencial, fora estabelecimento", "5"),
    NAOPRESENCIALOUTROS("9", "Operação não presencial, outros", "9");
    
    private String value;
    private String name;
    private String codigoReceita;

    TipoPresencaComprador(String value, String name, String codigoReceita) {
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

    public static TipoPresencaComprador fromValue(String value) {
        for (TipoPresencaComprador tipo : TipoPresencaComprador.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoPresencaComprador.values().length; i++) {
			TipoPresencaComprador tipoOperacao = TipoPresencaComprador.values()[i];

			list.add(EnumResponse.builder().name(tipoOperacao.getName()).value(tipoOperacao.getValue()).build());
		}

		return list;
	}
}
