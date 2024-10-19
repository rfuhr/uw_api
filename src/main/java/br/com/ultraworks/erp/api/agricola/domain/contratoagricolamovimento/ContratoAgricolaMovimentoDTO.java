package br.com.ultraworks.erp.api.agricola.domain.contratoagricolamovimento;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ContratoAgricolaMovimentoDTO {

	private Long id;
	private Long contratoAgricolaId;
	private Long operacaoInternaId;
	private int numeroNota;
	private LocalDate dataMovimento;
	private BigDecimal quantidade;
	private BigDecimal valor;

	private String operacaoInternaNome;
}
