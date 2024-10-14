package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.validaprecoagricolaitem.ValidaPrecoAgricolaItem;
import br.com.ultraworks.erp.api.agricola.domain.validaprecoagricolaitem.ValidaPrecoAgricolaItemDTO;
import br.com.ultraworks.erp.api.agricola.mapper.ValidaPrecoAgricolaItemMapper;
import br.com.ultraworks.erp.api.agricola.service.ValidaPrecoAgricolaItemService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/valida-preco-agricola-item")
public class ValidaPrecoAgricolaItemController
		extends GenericController<ValidaPrecoAgricolaItem, Long, ValidaPrecoAgricolaItemDTO> {

	public ValidaPrecoAgricolaItemController(ValidaPrecoAgricolaItemService service,
			ValidaPrecoAgricolaItemMapper mapper) {
		super(service, mapper);
	}

}