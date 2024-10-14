package br.com.ultraworks.erp.api.configuracao.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.configuracao.domain.configsistema.ConfigSistema;
import br.com.ultraworks.erp.api.configuracao.domain.configsistema.ConfigSistemaDTO;
import br.com.ultraworks.erp.api.configuracao.mapper.ConfigSistemaFinanceiroMapper;
import br.com.ultraworks.erp.api.configuracao.mapper.ConfigSistemaMapper;
import br.com.ultraworks.erp.api.configuracao.service.ConfigSistemaFinanceiroService;
import br.com.ultraworks.erp.api.configuracao.service.ConfigSistemaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/configuracao/sistema")
public class ConfigSistemaController extends GenericController<ConfigSistema, Long, ConfigSistemaDTO> {

	public ConfigSistemaController(ConfigSistemaService service, ConfigSistemaMapper mapper,
			ConfigSistemaFinanceiroService configSistemaFinanceiroService,
			ConfigSistemaFinanceiroMapper configSistemaFinanceiroMapper) {
		super(service, mapper);
	}

}