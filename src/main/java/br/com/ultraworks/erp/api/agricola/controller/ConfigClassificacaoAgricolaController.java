package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.configclassificacaoagricola.ConfigClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.configclassificacaoagricola.ConfigClassificacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.ConfigClassificacaoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.ConfigClassificacaoAgricolaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/config-classificacao-agricola")
public class ConfigClassificacaoAgricolaController
		extends GenericController<ConfigClassificacaoAgricola, Long, ConfigClassificacaoAgricolaDTO> {

	public ConfigClassificacaoAgricolaController(ConfigClassificacaoAgricolaService service,
			ConfigClassificacaoAgricolaMapper mapper) {
		super(service, mapper);
	}

}