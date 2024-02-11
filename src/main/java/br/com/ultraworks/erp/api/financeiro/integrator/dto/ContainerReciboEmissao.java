package br.com.ultraworks.erp.api.financeiro.integrator.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerReciboEmissao {

	private Long reciboEmissaoId;
	private Long bancoId;
	private Long agenciaId;
	private Long contaId;
	private Long tipoDocumentoId;
	private LocalDate dataVencimento;
	private BigDecimal valorRecibo;
	private int numeroRecibo;
	private LocalDate dataMovimeto;
}
