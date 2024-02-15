package br.com.ultraworks.erp.api.estoque.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ItemRepository extends UWRepository<Item, Long> {

}
