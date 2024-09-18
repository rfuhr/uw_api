package br.com.ultraworks.erp.api.financeiro.domain.titulo.request;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SelecionadosBaixaTituloRequest {

	private boolean parcial; 
	private Long tituloId;
	private Long parcelaFinanceiroId;
	private Long movimentoFinanceiroId;
	private BigDecimal valorBaixa;
	private BigDecimal valorJuros;
	private BigDecimal valorDesconto;
	private BigDecimal valorTotal;
	private List<BaixaTituloOperacaoAcessoriaRequest> juros;
	private List<BaixaTituloOperacaoAcessoriaRequest> descontos;
}
