package br.com.ultraworks.erp.api.financeiro.integrator.dto;

import br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira.OperacaoAcessoriaFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaomovimentofinanceiro.OperacaoMovimentoFinanceiro;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerEstornoMovimento {

	private Long idMovimentoEstorno;
	private OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiro;
	private OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceira;
}
