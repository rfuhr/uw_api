package br.com.ultraworks.erp.api.compras.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SolicitacaoMercadoriaParaCotacaoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -682165324001147554L;
	Long id;
	Long numero;
	LocalDate dataSolicitacao;
	Long departamentoSolicitadoId;
	String departamentoSolicitadoSigla;
	Long departamentoSolicitanteId;
	String departamentoSolicitanteSigla;
	
	List<SolicitacaoMercadoriaItemParaCotacaoVO> itens;
}
