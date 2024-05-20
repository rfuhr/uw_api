package br.com.ultraworks.erp.api.estoque.domain.saldoestoque;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class SaldoEstoqueResponse {

	private Long empresaFilialId;
	private String empresaFilialNome;
	private Long grupoContabilId;
	private String grupoContabilNome;
	private Long localEstoqueId;
	private String localEstoqueNome;
	private Long itemId;
	private String itemNome;
	private String unidadeMedidaSigla;
	private LocalDate data;
	private BigDecimal saldoQuantidade;
	private BigDecimal saldoValor;
	private BigDecimal custoMedio;
	
}
