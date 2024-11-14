package br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaitem;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class CotacaoMercadoriaItemDTO {

	private Long id;
	private Long cotacaoMercadoriaParceiroId;
	private Long itemId;
	private Long itemSimplificadoId;
	private String status;
	private Long solicitacaoMercadoriaItemId;

	private Long solicitacaoMercadoriaId;
	private Long solicitacaoMercadoriaNumero;
	private String solicitacaoMercadoriaDepartamentoSolicitanteSigla;
	private LocalDate solicitacaoMercadoriaDataSolicitacao;
	
	private String itemNome;
	private String statusNome;
	private String itemSimplificadoNome;
	private Long solicitacaoMercadoriaItemDepartamentoEntregaId;
	private String solicitacaoMercadoriaItemDepartamentoEntregaSigla;
	private BigDecimal solicitacaoMercadoriaItemQuantidadeSolicitada;
	private String solicitacaoMercadoriaItemObservacao;
	private String solicitacaoMercadoriaItemUsuarioSolicitacaoNome;
	private int solicitacaoMercadoriaItemPrevisaoDiasUtilizacao;
	private String solicitacaoMercadoriaItemUrgenciaSolicitacao;
	
}
