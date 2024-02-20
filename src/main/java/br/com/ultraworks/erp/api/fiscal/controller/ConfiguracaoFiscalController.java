package br.com.ultraworks.erp.api.fiscal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.ConfiguracaoFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.ConfiguracaoFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfiguracaoFiscalMapper;
import br.com.ultraworks.erp.api.fiscal.service.ConfiguracaoFiscalService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/fiscal/configuracao-fiscal")
public class ConfiguracaoFiscalController extends GenericController<ConfiguracaoFiscal, Long, ConfiguracaoFiscalDTO> {

	public ConfiguracaoFiscalController(ConfiguracaoFiscalService service, ConfiguracaoFiscalMapper mapper) {
		super(service, mapper);
	}

}