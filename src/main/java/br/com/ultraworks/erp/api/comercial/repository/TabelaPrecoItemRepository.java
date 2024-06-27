package br.com.ultraworks.erp.api.comercial.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.comercial.domain.tabelaprecoitem.TabelaPrecoItem;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface TabelaPrecoItemRepository extends UWRepository<TabelaPrecoItem, Long> {
	
	List<TabelaPrecoItem> findByTabelaPrecoId(Long tabelaPrecoId);

}
