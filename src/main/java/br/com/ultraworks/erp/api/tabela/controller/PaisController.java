package br.com.ultraworks.erp.api.tabela.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.tabela.domain.pais.Pais;
import br.com.ultraworks.erp.api.tabela.domain.pais.PaisDTO;
import br.com.ultraworks.erp.api.tabela.mapper.PaisMapper;
import br.com.ultraworks.erp.api.tabela.service.PaisService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/tabela/pais")
public class PaisController extends GenericController<Pais, Long, PaisDTO> {

	public PaisController(PaisService service, PaisMapper mapper) {
		super(service, mapper);
	}

}
