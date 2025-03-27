package br.com.ultraworks.erp.api.tabela.domain.operacaointernafinanceiro;

import lombok.Data;

@Data
public class OperacaoInternaFinanceiroDTO {

	private Long id;
	private Long operacaoInternaId;
	private Long indiceFinanceiroPadraoId;
	private String indiceFinanceiroPadraoNome;
	private Long tipoTituloId;
	private String tipoTituloNome;
	private Long grupoFinanceiroId;
	private String grupoFinanceiroNome;
	private Long caracteristicaMovimentoFinanceiroId;
	private String caracteristicaMovimentoFinanceiroNome;
	private Long historicoPadraoId;
	private String historicoPadraoNome;
	private Long carteiraFinanceiraId;
	private String carteiraFinanceiraNome;
	private Long fatoGeradorId;
	private String fatoGeradorNome;
	private Long motivoEstornoFinanceiroId;
	private String motivoEstornoFinanceiroNome;
}
