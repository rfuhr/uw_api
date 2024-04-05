package br.com.ultraworks.erp.api.fiscal.domain.tipoprocessoemissao;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum TipoProcessoEmissao {
    APLICATIVOPROPRIO("0", "Emissão de NF-e com aplicativo do contribuinte", "0"),
    FISCO("1", "Emissão de NF-e avulsa pelo fisco", "1"),
    AVULSA("2", "Emissão de NF-e, pelo contribuinte com seu certificado digial, através do site do fisco", "2"),
    APLICATIVOFISCO("3", "Emissão de NF-e pelo contribuinte com aplicativo fornecido pelo fisco", "3");
    
    private String value;
    private String name;
    private String codigoReceita;

    TipoProcessoEmissao(String value, String name, String codigoReceita) {
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

    public static TipoProcessoEmissao fromValue(String value) {
        for (TipoProcessoEmissao tipo : TipoProcessoEmissao.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoProcessoEmissao.values().length; i++) {
			TipoProcessoEmissao tipoOperacao = TipoProcessoEmissao.values()[i];

			list.add(EnumResponse.builder().name(tipoOperacao.getName()).value(tipoOperacao.getValue()).build());
		}

		return list;
	}
}
