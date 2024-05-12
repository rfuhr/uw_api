package br.com.ultraworks.erp.api.estoque.domain.movimentoestoque;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AtualizaEstoqueItensRequest {

	private Long itemId;
	private BigDecimal quantidade;
	private BigDecimal custoMedio;
	private BigDecimal valor;
	
}
