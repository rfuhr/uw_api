package br.com.ultraworks.erp.api.financeiro.domain.saldocaixabanco;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SaldoCaixaBancoRequest {
	
	private Long bancoId;
	private Long agenciaId;
	private Long contaId;
	private LocalDate dataInicio;
	private LocalDate dataFinal;

}
