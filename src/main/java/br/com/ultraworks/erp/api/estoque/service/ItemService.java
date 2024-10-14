package br.com.ultraworks.erp.api.estoque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.repository.ValidaItemClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.estoque.domain.item.ItemDTO;
import br.com.ultraworks.erp.api.estoque.mapper.ItemMapper;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ItemService extends GenericService<Item, Long, ItemDTO> {

	@Autowired
	public ItemService(ItemRepository repository, ItemMapper mapper,
			ValidaItemClassificacaoAgricolaRepository validaItemClassificacaoAgricolaRepository) {
		super(repository, mapper);
	}

	public int getProximoCodigo() {
		return ((ItemRepository) repository).getProximoCodigo();
	}
}