package br.com.ultraworks.erp.api.estoque.domain.movimentoestoque;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class MovimentoEstoqueResponse {

	private Long empresaFilialId;
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
	private String unidadeMedidaSigla;
	private Long operacaoInternaId;
	private String operacaoInternaNome;
	private String operacaoInternaSigla;
	private LocalDate data;
	private String documento;
	private String tipoMovimentoEstoque;
	private BigDecimal quantidade;
	private String creditoDebitoQuantidade;
	private BigDecimal valor;
	private String creditoDebitoValor;
	private BigDecimal custoMedio;
	private boolean entrada;
	
}
