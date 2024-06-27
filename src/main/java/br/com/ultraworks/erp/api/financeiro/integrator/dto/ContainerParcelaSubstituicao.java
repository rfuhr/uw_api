package br.com.ultraworks.erp.api.financeiro.integrator.dto;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira.OperacaoAcessoriaFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaomovimentofinanceiro.OperacaoMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.parametrofinanceiro.ParametroFinanceiro;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerParcelaSubstituicao {

	private Long parcelaFinanceiraId;
	private Long idMovimentoParaBaixa;
	private BigDecimal valorBaixa;
	private CarteiraFinanceira carteiraFinanceiraSubstituicao;
	private boolean criaSubSequenciaMovimento;
	private ParametroFinanceiro parametroFinanceiro;
	private OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiro;
	private OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceira;

}
