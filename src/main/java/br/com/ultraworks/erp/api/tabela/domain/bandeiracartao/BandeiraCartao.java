package br.com.ultraworks.erp.api.tabela.domain.bandeiracartao;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.api.tabela.domain.indicadoroperacao.IndicadorOperacao;
import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum BandeiraCartao {
	VISA("01", "Visa"),
	MASTERCARD("02", "Mastercard"),
	AMERICANEXPRESS("03", "American Express"),
	SOROCRED("04", "Sorocred"),
	DINERSCLUB("05", "Diners Club"),
	ELO("06", "Elo"),
	HIPERCARD("07", "Hipercard"),
	AURA("08", "Aura"),
	CABAL("09", "Cabal"),
	OUTROS("99", "Outros");

	private String value;
	private String name;

	BandeiraCartao(String value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public static BandeiraCartao fromValue(String value) {
		for (BandeiraCartao tipo : BandeiraCartao.values()) {
			if (tipo.getValue().equalsIgnoreCase(value)) {
				return tipo;
			}
		}
		return null; 
	}
	
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < BandeiraCartao.values().length; i++) {
			BandeiraCartao tipo = BandeiraCartao.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
