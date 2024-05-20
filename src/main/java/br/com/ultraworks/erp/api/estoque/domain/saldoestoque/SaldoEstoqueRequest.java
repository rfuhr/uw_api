package br.com.ultraworks.erp.api.estoque.domain.saldoestoque;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SaldoEstoqueRequest {
	
	private Long empresaFilialId;
	private Long grupoContabilId;
	private Long localEstoqueId;
	private Long itemId;
	private LocalDate dataInicio;
	private LocalDate dataFinal;

}
