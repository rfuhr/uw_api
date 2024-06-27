package br.com.ultraworks.erp.api.financeiro.integrator.dto;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira.OperacaoAcessoriaFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaomovimentofinanceiro.OperacaoMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.parametrofinanceiro.ParametroFinanceiro;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerParcelaBaixa {

	private OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiro;
	private OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceira;
	private Long parcelaFinanceiraId;
	private Long idMovimentoParaBaixa;
	private BigDecimal valorBaixa;
	private boolean criaSubSequenciaMovimento;
	private ParametroFinanceiro parametroFinanceiro;

}
