package br.com.ultraworks.erp.api.comercial.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupitem.ConfigMarkupItem;
import br.com.ultraworks.erp.api.comercial.domain.configmarkupitem.ConfigMarkupItemDTO;
import br.com.ultraworks.erp.api.comercial.mapper.ConfigMarkupItemMapper;
import br.com.ultraworks.erp.api.comercial.service.ConfigMarkupItemService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/comercial/config-markup-item")
public class ConfigMarkupItemController extends GenericController<ConfigMarkupItem, Long, ConfigMarkupItemDTO> {

	public ConfigMarkupItemController(ConfigMarkupItemService service, ConfigMarkupItemMapper mapper) {
		super(service, mapper);
	}

}