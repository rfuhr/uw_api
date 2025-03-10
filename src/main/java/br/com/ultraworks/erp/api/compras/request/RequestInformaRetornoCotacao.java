package br.com.ultraworks.erp.api.compras.request;

import java.util.List;

import lombok.Data;

@Data
public class RequestInformaRetornoCotacao {

	private Long cotacaoMercadoriaId;
	private Long cotacaoMercadoriaParceiroId;
	private Integer previsaoDiasEntrega;
	private Long condicaoPagamentoId;
	private String meioPagamento;
	private String situacao;
	
	private List<RequestInformaRetornoCotacaoItem> itens;
}
