package br.com.ultraworks.erp.api.comercial.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitem.ConfigMarkupPlanoItem;
import br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitem.ConfigMarkupPlanoItemDTO;
import br.com.ultraworks.erp.api.comercial.mapper.ConfigMarkupPlanoItemMapper;
import br.com.ultraworks.erp.api.comercial.service.ConfigMarkupPlanoItemService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/comercial/config-markup-plano-item")
public class ConfigMarkupPlanoItemController extends GenericController<ConfigMarkupPlanoItem, Long, ConfigMarkupPlanoItemDTO> {

	public ConfigMarkupPlanoItemController(ConfigMarkupPlanoItemService service, ConfigMarkupPlanoItemMapper mapper) {
		super(service, mapper);
	}

}