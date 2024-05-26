package br.com.ultraworks.erp.api.comercial.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.comercial.domain.configcalculopreco.ConfigCalculoPreco;
import br.com.ultraworks.erp.api.comercial.domain.configcalculopreco.ConfigCalculoPrecoDTO;
import br.com.ultraworks.erp.api.comercial.mapper.ConfigCalculoPrecoMapper;
import br.com.ultraworks.erp.api.comercial.service.ConfigCalculoPrecoService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/comercial/config-calculo-preco")
public class ConfigCalculoPrecoController extends GenericController<ConfigCalculoPreco, Long, ConfigCalculoPrecoDTO> {

	public ConfigCalculoPrecoController(ConfigCalculoPrecoService service, ConfigCalculoPrecoMapper mapper) {
		super(service, mapper);
	}

}