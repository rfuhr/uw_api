package br.com.ultraworks.erp.api.agricola.domain.classificacaoagricola;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RecalcularClassificacaoAgricolaRequest {

	private Long produtoId;
	private Long safraId;
	private Long tipoClassificacaoAgricolaId;
	private Long pesoBaseCalculo;
	private BigDecimal valor;
	
}
