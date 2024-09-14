package br.com.ultraworks.erp.api.agricola.domain.classificacaoagricola;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecalcularClassificacaoAgricolaResponse {

	private Long produtoId;
	private Long safraId;
	private Long tipoClassificacaoAgricolaId;
	private Long pesoBaseCalculo;
	private BigDecimal valor;
	private BigDecimal faixaInicial;
	private BigDecimal faixaFinal;
	private Long quantidadeDesconto;
	private BigDecimal percentualDesconto;
	private Long configClassificacaoAgricolaId;
}
