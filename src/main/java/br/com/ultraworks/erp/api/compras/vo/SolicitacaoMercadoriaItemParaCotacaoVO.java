package br.com.ultraworks.erp.api.compras.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SolicitacaoMercadoriaItemParaCotacaoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2175996270500158524L;
	Long id;
	Long solicitacaoMercadoriaId;
	Long itemId;
	String itemNome;
	Long itemSimplificadoId;
	String itemSimplificadoNome;
	Long departamentoEntregaId;
	String departamentoEntregaSigla;
	BigDecimal quantidadeSolicitada;
	String observacao;
	String usuarioSolicitacaoNome;
	int previsaoDiasUtilizacao;
	String urgenciaSolicitacaoMercadoria;
	
	
}
