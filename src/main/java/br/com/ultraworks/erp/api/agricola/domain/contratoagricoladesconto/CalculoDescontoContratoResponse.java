package br.com.ultraworks.erp.api.agricola.domain.contratoagricoladesconto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalculoDescontoContratoResponse {

	private Long contratoAgricolaId;
	private Long itemId;
	private Long departamentoId;
	private Long tipoCalculoAgricolaId;
	private BigDecimal percentualTaxaContrato;
	private BigDecimal percentualTaxaAtual;
	private BigDecimal valor;
	
}
