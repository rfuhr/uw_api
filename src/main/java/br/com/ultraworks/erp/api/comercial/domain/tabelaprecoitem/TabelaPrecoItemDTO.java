package br.com.ultraworks.erp.api.comercial.domain.tabelaprecoitem;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class TabelaPrecoItemDTO {

	private Long id;
	private Long tabelaPrecoId;
	private String tabelaPrecoNome;
	private int tabelaPrecoCodigo;
	private Long itemId;
	private String itemNome;
	private int itemCodigo;
	private BigDecimal valorCusto;
	private BigDecimal valorMarkup;
	private BigDecimal valorCalculado;
	private BigDecimal valorAtual;
	private BigDecimal valor;
	private BigDecimal percentualMaximoDesconto;
	
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;
	
}
