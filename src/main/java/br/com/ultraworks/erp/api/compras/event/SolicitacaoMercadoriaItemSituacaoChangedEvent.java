package br.com.ultraworks.erp.api.compras.event;

import java.util.List;

public class SolicitacaoMercadoriaItemSituacaoChangedEvent {

	private final Long solicitacaoMercadoriaId;
	private final List<Long> solicitacaoMercadoriaIds;

	public SolicitacaoMercadoriaItemSituacaoChangedEvent(Long solicitacaoMercadoriaId) {
		this.solicitacaoMercadoriaId = solicitacaoMercadoriaId;
		this.solicitacaoMercadoriaIds = null;
	}

	public SolicitacaoMercadoriaItemSituacaoChangedEvent(List<Long> solicitacaoMercadoriaIds) {
		this.solicitacaoMercadoriaId = null;
		this.solicitacaoMercadoriaIds = solicitacaoMercadoriaIds;
	}

	public Long getSolicitacaoMercadoriaId() {
		return solicitacaoMercadoriaId;
	}

	public List<Long> getSolicitacaoMercadoriaIds() {
		return solicitacaoMercadoriaIds;
	}

}
