package br.com.ultraworks.erp.api.financeiro.integrator.dto;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.financeiro.domain.operacaofinanceira.OperacaoFinanceira;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerEstornoMovimento {

	private Long idMovimentoEstorno;
	private Long motivoEstornoId;
	private OperacaoFinanceira operacaoFinanceira;
	private Long reciboEmissaoId;
	private String observacaoEstorno;
	private LocalDate dataEstorno;
	private boolean compoeSaldo;
	private Departamento departamento;
}
