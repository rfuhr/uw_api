package br.com.ultraworks.erp.api.financeiro.integrator.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.financeiro.domain.conta.Conta;
import br.com.ultraworks.erp.api.financeiro.domain.motivoestornofinanceiro.MotivoEstornoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira.TipoOperacaoFinanceira;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerDefinicaoOperacao {

	private TipoOperacaoFinanceira tipoOperacaoFinanceira;
	private LocalDate dataMovimento;
	private BigDecimal valorOperacao;
	private Departamento departamentoPelaOperacao;
	private String observacao;
	private MotivoEstornoFinanceiro motivoEstornoFinanceiro;
	private Conta conta;
}
