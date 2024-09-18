package br.com.ultraworks.erp.api.financeiro.domain.relatorio.request;

import java.time.LocalDate;

import br.com.ultraworks.erp.core.relatorios.ISelecaoRelatorioRequest;
import lombok.Data;

@Data
public class SelecaoRelatorioFinanceiroRequest implements ISelecaoRelatorioRequest {

	private String tipoRelatorioFinanceiro;
	private Long tipoTituloId;
	private LocalDate vencimentoInicial;
	private LocalDate vencimentoFinal;
	private LocalDate competencia;
	private Long departamentoId;
	private Long caracteristicaMovimentoFinanceiroId;
	private Long parceiroLocalId;
	private Long carteiraFinanceiraId;
	private Long tipoOperacaoFinanceiraId;
	private LocalDate dataMovimentoInicial;
	private LocalDate dataMovimentoFinal;
	
}
