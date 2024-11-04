package br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaitem;

import lombok.Data;

@Data
public class CotacaoMercadoriaItemDTO {

	private Long id;
	private Long cotacaoMercadoriaParceiroId;
	private Long itemId;
	private Long itemSimplificadoId;
	private Long solicitacaoMercadoriaItemId;

	private String itemNome;
	private String itemSimplificadoNome;
}
