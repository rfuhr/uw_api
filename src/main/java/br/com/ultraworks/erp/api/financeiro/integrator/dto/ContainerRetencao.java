package br.com.ultraworks.erp.api.financeiro.integrator.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerRetencao {

	private int tipoImpostoId;
	private BigDecimal baseCalculo;
	private BigDecimal valorRetencao;
	private int parceiroLocalId;
	private Date dataVencimento;
}
