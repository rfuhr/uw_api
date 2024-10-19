package br.com.ultraworks.erp.api.configuracao.domain.configsistemafinanceiro;

import lombok.Data;

@Data
public class ConfigSistemaFinanceiroDTO {

	private Long id;
	private Long configSistemaId;
	private Long tipoTituloReceberId;
	private Long tipoTituloPagarId;
	private Long operacaoMovimentoFinanceiroInclusaoId;
	private Long operacaoMovimentoFinanceiroBaixaId;
	private Long operacaoAcessoriaFinanceiraPrincipalId;
	private Long operacaoAcessoriaFinanceiraJurosPactuadoId;
	private Long tipoOperacaoFinanceiraLancamentoId;
	private Long tipoOperacaoFinanceiraEstornoId;
	private Long grupoFinanceiroNegociacaoId;
	private Long fatoGeradorNegociacaoId;
	private Long carteiraFinanceiraNegociacaoId;
	private Long caracteristicaMovimentoFinanceiroNegociacaoId;
	private Long historicoPadraoNegociacaoId;
}
