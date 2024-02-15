package br.com.ultraworks.erp.api.fiscal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.regimetributario.RegimeTributario;
import br.com.ultraworks.erp.api.fiscal.domain.regimetributario.RegimeTributarioDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.RegimeTributarioMapper;
import br.com.ultraworks.erp.api.fiscal.service.RegimeTributarioService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/fiscal/regime-tributario")
public class RegimeTributarioController extends GenericController<RegimeTributario, Long, RegimeTributarioDTO> {

	public RegimeTributarioController(RegimeTributarioService service, RegimeTributarioMapper mapper) {
		super(service, mapper);
	}

}