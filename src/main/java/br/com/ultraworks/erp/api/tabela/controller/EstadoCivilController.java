package br.com.ultraworks.erp.api.tabela.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.tabela.domain.estadocivil.EstadoCivil;
import br.com.ultraworks.erp.api.tabela.domain.estadocivil.EstadoCivilDTO;
import br.com.ultraworks.erp.api.tabela.mapper.EstadoCivilMapper;
import br.com.ultraworks.erp.api.tabela.service.EstadoCivilService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/tabela/estado-civil")
public class EstadoCivilController extends GenericController<EstadoCivil, Long, EstadoCivilDTO> {

	public EstadoCivilController(EstadoCivilService service, EstadoCivilMapper mapper) {
		super(service, mapper);
	}

}
