package br.com.ultraworks.erp.api.estoque.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.estoque.domain.item.ItemDTO;
import br.com.ultraworks.erp.api.estoque.mapper.ItemMapper;
import br.com.ultraworks.erp.api.estoque.service.ItemService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/estoque/item")
public class ItemController extends GenericController<Item, Long, ItemDTO> {

	public ItemController(ItemService service, ItemMapper mapper) {
		super(service, mapper);
	}

}