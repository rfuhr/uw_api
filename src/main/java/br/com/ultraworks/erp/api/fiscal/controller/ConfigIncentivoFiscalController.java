package br.com.ultraworks.erp.api.fiscal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscal.ConfigIncentivoFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscal.ConfigIncentivoFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfigIncentivoFiscalMapper;
import br.com.ultraworks.erp.api.fiscal.service.ConfigIncentivoFiscalService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/fiscal/config-incentivo-fiscal")
public class ConfigIncentivoFiscalController extends GenericController<ConfigIncentivoFiscal, Long, ConfigIncentivoFiscalDTO> {

	public ConfigIncentivoFiscalController(ConfigIncentivoFiscalService service, ConfigIncentivoFiscalMapper mapper) {
		super(service, mapper);
	}

}