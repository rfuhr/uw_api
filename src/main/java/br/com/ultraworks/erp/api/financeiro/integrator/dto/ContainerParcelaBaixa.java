package br.com.ultraworks.erp.api.financeiro.integrator.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaofinanceira.OperacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.parametrofinanceiro.ParametroFinanceiro;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerParcelaBaixa {

	private Departamento departamento;
	private OperacaoFinanceira operacaoFinanceira;
	private Long idMovimentoParaBaixa;
	private Long bancoId;
	private Long agenciaId;
	private Long contaId;
	private BigDecimal valorBaixa;
	private CarteiraFinanceira carteiraFinanceiraSubstituicao;
	private LocalDate dataMovimento;
	private boolean criaSubSequenciaMovimento;
	private String observacao;
	private ParametroFinanceiro parametroFinanceiro;

}
