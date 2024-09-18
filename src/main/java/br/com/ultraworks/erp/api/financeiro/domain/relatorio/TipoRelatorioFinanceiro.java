package br.com.ultraworks.erp.api.financeiro.domain.relatorio;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;
import br.com.ultraworks.erp.core.relatorios.TipoRelatorio;

public enum TipoRelatorioFinanceiro implements TipoRelatorio {
	POSICAOTITULOABERTO("POSTITULOABERTO", "Posição de Títulos em Aberto"),
	ANALITICOADTODEVABERTO("ANALITICOADTODEVABERTO", "Relatório Analítico Adiantamento/Devoluções em Aberto"),
	POSICAOTITULOBAIXADOS("POSTITULOBAIXADOS", "Posição de Títulos Baixados"),
	POSICAOTITULOABERTOVENCIMENTO("POSTITULOABERTOVENCIMENTO", "Posição de Títulos em Aberto por Vencimento"),
	SINTETICOGERAL("SINTETICOGERAL", "Sintético - Geral"),
	SINTETICOABERTO("SINTETICOABERTO", "Sintético em Aberto"),
	SINTETICOVENCIDOS("SINTETICOVENCIDOS", "Sintético - Vencidos");

	private String value;
	private String name;

	TipoRelatorioFinanceiro(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static TipoRelatorioFinanceiro fromValue(String value) {
		for (TipoRelatorioFinanceiro tipo : TipoRelatorioFinanceiro.values()) {
			if (tipo.getValue().equalsIgnoreCase(value)) {
				return tipo;
			}
		}
		return null;
	}

	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoRelatorioFinanceiro.values().length; i++) {
			TipoRelatorioFinanceiro tipoRelatorio = TipoRelatorioFinanceiro.values()[i];

			list.add(EnumResponse.builder().name(tipoRelatorio.getName()).value(tipoRelatorio.getValue()).build());
		}

		return list;
	}
}
