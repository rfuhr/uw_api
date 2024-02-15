package br.com.ultraworks.erp.api.seguranca.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.seguranca.domain.autonomia.Autonomia;
import br.com.ultraworks.erp.api.seguranca.domain.autonomia.AutonomiaDTO;
import br.com.ultraworks.erp.api.seguranca.mapper.AutonomiaMapper;
import br.com.ultraworks.erp.api.seguranca.service.AutonomiaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/seguranca/autonomia")
public class AutonomiaController extends GenericController<Autonomia, Long, AutonomiaDTO> {

	public AutonomiaController(AutonomiaService service, AutonomiaMapper mapper) {
		super(service, mapper);
	}
}