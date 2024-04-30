package br.com.ultraworks.erp.api.fiscal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.mensagemfiscal.MensagemFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.mensagemfiscal.MensagemFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.MensagemFiscalMapper;
import br.com.ultraworks.erp.api.fiscal.service.MensagemFiscalService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/fiscal/mensagem-fiscal")
public class MensagemFiscalController extends GenericController<MensagemFiscal, Long, MensagemFiscalDTO> {

	public MensagemFiscalController(MensagemFiscalService service, MensagemFiscalMapper mapper) {
		super(service, mapper);
	}

}