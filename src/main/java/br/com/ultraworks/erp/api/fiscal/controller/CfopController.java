package br.com.ultraworks.erp.api.fiscal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.cfop.Cfop;
import br.com.ultraworks.erp.api.fiscal.domain.cfop.CfopDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.CfopMapper;
import br.com.ultraworks.erp.api.fiscal.service.CfopService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/fiscal/cfop")
public class CfopController extends GenericController<Cfop, Long, CfopDTO> {

	public CfopController(CfopService service, CfopMapper mapper) {
		super(service, mapper);
	}

}