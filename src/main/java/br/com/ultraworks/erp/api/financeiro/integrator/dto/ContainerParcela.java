package br.com.ultraworks.erp.api.financeiro.integrator.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerParcela {

	private int numeroParcela;
	private int sequenciaParcela;
	private LocalDate dataVencimento;
	private BigDecimal valorParcela;
}
