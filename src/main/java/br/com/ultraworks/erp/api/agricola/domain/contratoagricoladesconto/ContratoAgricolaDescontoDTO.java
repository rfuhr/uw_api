package br.com.ultraworks.erp.api.agricola.domain.contratoagricoladesconto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ContratoAgricolaDescontoDTO {

	private Long id;
	private Long contratoAgricolaId;
	private Long tipoCalculoAgricolaId;
	private BigDecimal percentualTaxaContrato;
	private BigDecimal percentualTaxaAtual;
	private BigDecimal valor;
	
	private String tipoCalculoAgricolaNome;
}
