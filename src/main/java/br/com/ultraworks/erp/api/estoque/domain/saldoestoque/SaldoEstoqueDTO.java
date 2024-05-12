package br.com.ultraworks.erp.api.estoque.domain.saldoestoque;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class SaldoEstoqueDTO {

	private Long id;
	private Long empresaFilialId;
	private String empresaFilialSigla;
	private String empresaFilialNome;
	private Long grupoContabilId;
	private int grupoContabilCodigo;
	private String grupoContabilNome;
	private Long localEstoqueId;
	private int localEstoqueCodigo;
	private String localEstoqueNome;
	private Long itemId;
	private int itemCodigo;
	private String itemNome;
	
	private LocalDate data;
	private BigDecimal saldoQuantidade;
	private BigDecimal saldoValor;
	private BigDecimal custoMedio;
}
