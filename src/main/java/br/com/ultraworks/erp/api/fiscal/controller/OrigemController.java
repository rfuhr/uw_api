package br.com.ultraworks.erp.api.fiscal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.origem.Origem;
import br.com.ultraworks.erp.api.fiscal.domain.origem.OrigemDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.OrigemMapper;
import br.com.ultraworks.erp.api.fiscal.service.OrigemService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/fiscal/origem")
public class OrigemController extends GenericController<Origem, Long, OrigemDTO> {

	public OrigemController(OrigemService service, OrigemMapper mapper) {
		super(service, mapper);
	}

}