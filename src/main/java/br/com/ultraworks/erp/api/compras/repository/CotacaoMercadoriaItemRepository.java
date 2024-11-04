package br.com.ultraworks.erp.api.compras.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaitem.CotacaoMercadoriaItem;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface CotacaoMercadoriaItemRepository extends UWRepository<CotacaoMercadoriaItem, Long> {

	List<CotacaoMercadoriaItem> findByCotacaoMercadoriaParceiroId(Long cotacaoMercadoriaParceiroId);
}
