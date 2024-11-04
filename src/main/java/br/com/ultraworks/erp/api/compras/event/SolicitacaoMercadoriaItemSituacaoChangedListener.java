package br.com.ultraworks.erp.api.compras.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.compras.service.solicitacaomercadoria.SolicitacaoMercadoriaService;

@Component
public class SolicitacaoMercadoriaItemSituacaoChangedListener {

	private final SolicitacaoMercadoriaService solicitacaoMercadoriaService;
	
	public SolicitacaoMercadoriaItemSituacaoChangedListener(SolicitacaoMercadoriaService solicitacaoMercadoriaService) {
		this.solicitacaoMercadoriaService = solicitacaoMercadoriaService;
	}
	
	@EventListener
    public void handleSolicitacaoMercadoriaItemSituacaoChanged(SolicitacaoMercadoriaItemSituacaoChangedEvent event) {
		if (event.getSolicitacaoMercadoriaId() != null)
			solicitacaoMercadoriaService.atualizarStatusSolicitacao(event.getSolicitacaoMercadoriaId());
		if (event.getSolicitacaoMercadoriaIds() != null)
			solicitacaoMercadoriaService.atualizarStatusSolicitacoes(event.getSolicitacaoMercadoriaIds());
		
    }
}
