package br.com.ultraworks.erp.api.agricola.domain.configclassificacaoagricola;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ConfigClassificacaoAgricolaDTO {

	private Long id;
	private Long itemId;
	private Long tipoClassificacaoAgricolaId;
	private Long safraId;
	private BigDecimal faixaInicial;
	private BigDecimal faixaFinal;
	private boolean desconto;
	private BigDecimal percentualDesconto;
	
	private String itemNome;
	private String tipoClassificacaoAgricolaNome;
	private String safraNome;
}
