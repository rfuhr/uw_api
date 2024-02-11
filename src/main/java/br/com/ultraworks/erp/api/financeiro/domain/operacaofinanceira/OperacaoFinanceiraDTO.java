package br.com.ultraworks.erp.api.financeiro.domain.operacaofinanceira;

import lombok.Data;

@Data
public class OperacaoFinanceiraDTO {

	private Long id;
	private Long tipoOperacaoFinanceiraId;
	private Long operacaoMovimentoFinanceiroId;
	private Long operacaoAcessoriaFinanceiraId;

}
