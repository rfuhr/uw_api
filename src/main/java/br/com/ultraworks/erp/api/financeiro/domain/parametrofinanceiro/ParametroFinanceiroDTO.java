package br.com.ultraworks.erp.api.financeiro.domain.parametrofinanceiro;

import lombok.Data;

@Data
public class ParametroFinanceiroDTO {

	private Long id;
	private Long tipoTituloId;
	private Long caracteristicaMovimentoFinanceiroId;
	private Long carteiraFinanceiraId;
	private Long fatoGeradorId;
	private Long operacaoMovimentoFinanceiroId;
	private Long operacaoAcessoriaFinanceiraId;
	private String indicadorDC;
	private Long planoClassificacaoFinanceiraId;
	
	private String tipoTituloSigla;
	private String caracteristicaMovimentoFinanceiroSigla;
	private String carteiraFinanceiraSigla;
	private String fatoGeradorSigla;
	private String operacaoMovimentoFinanceiroNome;
	private String operacaoAcessoriaFinanceiraNome;
	private String planoClassificacaoFinanceiraCodigo;
}
