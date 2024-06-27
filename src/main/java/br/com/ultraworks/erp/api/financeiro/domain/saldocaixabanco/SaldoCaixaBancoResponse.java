package br.com.ultraworks.erp.api.financeiro.domain.saldocaixabanco;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class SaldoCaixaBancoResponse {

	private Long bancoId;
	private String bancoNome;
	private Long agenciaId;
	private String agenciaNome;
	private Long contaId;
	private String contaNome;
	private String contaNumero;
	private LocalDate data;
	private BigDecimal saldoValor;
	
}
