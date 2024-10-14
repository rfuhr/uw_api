package br.com.ultraworks.erp.api.agricola.integrator.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RomaneioSelecaoFixacaoDTO {

	private Long romaneioAgricolaId;
	private Long parceiroId;
	private Long parceiroLocalId;
	private Long parceiroLocalPropriedadeId;
	private Long itemId;
	private LocalDate dataMovimento;
	private Long numeroRomaneio;
	private Long departamentoId;
	private Long tipoPrecoAgricolaId;
	private BigDecimal quantidadeParcial;
	private BigDecimal saldoFixar;
	private Long operacaoInternaRomaneioId;
	private Long grupoOperacaoAgricolaRomaneioId;
	private Long safraId;
	private int numeroNfDeposito;
	private BigDecimal valorUnitario;
	private BigDecimal precoOrigem;
	private BigDecimal valorBruto;
	private BigDecimal valorLiquido;
	private BigDecimal descontoAcrescimo;
	private boolean fixar;
	private BigDecimal precoDeposito;
	
}
