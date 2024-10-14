package br.com.ultraworks.erp.api.tabela.domain.operacaointernafinanceiro;

import lombok.Data;

@Data
public class OperacaoInternaFinanceiroDTO {

	private Long id;
	private Long operacaoInternaId;
	private Long indiceFinanceiroPadraoId;

	private String indiceFinanceiroPadraoNome;
}
