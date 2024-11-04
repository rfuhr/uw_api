package br.com.ultraworks.erp.api.compras.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoriaitem.SolicitacaoMercadoriaItem;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface SolicitacaoMercadoriaItemRepository extends UWRepository<SolicitacaoMercadoriaItem, Long> {

	List<SolicitacaoMercadoriaItem> findBySolicitacaoMercadoriaId(Long solicitacaoMercadoriaId);
}
