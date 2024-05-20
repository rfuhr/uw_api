package br.com.ultraworks.erp.api.estoque.domain.movimentoestoque;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MovimentoEstoqueRequest {
	
	private Long empresaFilialId;
	private Long grupoContabilId;
	private Long localEstoqueId;
	private Long itemId;
	private Long operacaoInternaId;
	private String documento;
	private LocalDate dataInicio;
	private LocalDate dataFinal;

}
