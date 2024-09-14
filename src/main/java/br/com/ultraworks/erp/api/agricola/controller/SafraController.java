package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.safra.Safra;
import br.com.ultraworks.erp.api.agricola.domain.safra.SafraDTO;
import br.com.ultraworks.erp.api.agricola.mapper.SafraMapper;
import br.com.ultraworks.erp.api.agricola.service.SafraService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/safra")
public class SafraController extends GenericController<Safra, Long, SafraDTO> {

	public SafraController(SafraService service, SafraMapper mapper) {
		super(service, mapper);
	}

}