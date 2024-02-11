package br.com.ultraworks.erp.api.financeiro.integrator.dto;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira.TipoOperacaoFinanceira;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerDefinicaoOperacao {

	private TipoOperacaoFinanceira tipoOperacaoFinanceira;
	private BigDecimal valorOperacao;
	private Departamento departamentoPelaOperacao;
}
