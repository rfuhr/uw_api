package br.com.ultraworks.erp.api.compras.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RequestInformaRetornoCotacaoItem {

	private Long cotacaoMercadoriaItemId;
	private BigDecimal quantidadeCotada;
	private BigDecimal valorUnitario;
	private String status;
}
