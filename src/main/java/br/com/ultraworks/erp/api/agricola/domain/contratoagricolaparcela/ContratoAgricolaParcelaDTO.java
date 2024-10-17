package br.com.ultraworks.erp.api.agricola.domain.contratoagricolaparcela;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ContratoAgricolaParcelaDTO {

	private Long id;
	private Long contratoAgricolaId;
	private int numeroParcela;
	private LocalDate dataVencimento;
	private BigDecimal valorParcela;
	
}
