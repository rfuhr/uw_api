package br.com.ultraworks.erp.api.financeiro.domain.saldocaixabanco;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class SaldoCaixaBancoDTO {

	private Long id;
	private Long contaId;
	private LocalDate data;
	private BigDecimal saldoValor;
}
