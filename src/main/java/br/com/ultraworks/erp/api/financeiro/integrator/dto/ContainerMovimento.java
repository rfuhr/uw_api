package br.com.ultraworks.erp.api.financeiro.integrator.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.grupofinanceiro.GrupoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.operacaofinanceira.OperacaoFinanceira;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerMovimento {

	private int numeroParcela;
	private int sequenciaParcela;
	private int sequenciaMovimento;
	private int subSequenciaMovimento;
	private OperacaoFinanceira operacaoFinanceira;
	private CarteiraFinanceira carteiraFinanceira;
	private GrupoFinanceiro grupoFinanceiro;
	private BigDecimal valorMovimento;
	private LocalDate dataMovimento;
	private BigDecimal saldoParcela;
	private String observacao;
}
