package br.com.ultraworks.erp.api.fiscal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal.ConfigMensagemFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal.ConfigMensagemFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfigMensagemFiscalMapper;
import br.com.ultraworks.erp.api.fiscal.service.ConfigMensagemFiscalService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/fiscal/config-mensagem-fiscal")
public class ConfigMensagemFiscalController extends GenericController<ConfigMensagemFiscal, Long, ConfigMensagemFiscalDTO> {

	public ConfigMensagemFiscalController(ConfigMensagemFiscalService service, ConfigMensagemFiscalMapper mapper) {
		super(service, mapper);
	}

}