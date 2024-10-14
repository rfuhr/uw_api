package br.com.ultraworks.erp.api.agricola.integrator.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerConfiguracaoIntegracaoFixacaoParcelas {

	private LocalDate dataVencimento;
	private BigDecimal valorParcela;
}
