package br.com.ultraworks.erp.api.financeiro.integrator.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerReparcelamento {

	private int parcelaFinanceiraId;
	private int tituloFinanceiroId;
	private int numeroParcela;
	private int sequenciaParcela;
	private Date dataVencimento;
	private BigDecimal valorParcela;
	private int operacaoFinanceiraId;
}
