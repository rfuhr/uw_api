package br.com.ultraworks.erp.api.agricola.integrator.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecordAgrupamentoFixacao {

	private Long parceiroLocalId;
	private Long itemId;
	private Long departamentoId;
	private Long tipoPrecoAgricolaId;
	private BigDecimal classificacaoNivel1;
	private BigDecimal classificacaoNivel2;
	private BigDecimal classificacaoNivel3;
	private BigDecimal classificacaoNivel4;
	private BigDecimal quantidadeFinal;
	private int numeroNfDeposito;
}
