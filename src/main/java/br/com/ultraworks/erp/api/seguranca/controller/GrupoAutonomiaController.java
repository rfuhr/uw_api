package br.com.ultraworks.erp.api.seguranca.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.seguranca.domain.grupoautonomia.GrupoAutonomia;
import br.com.ultraworks.erp.api.seguranca.domain.grupoautonomia.GrupoAutonomiaDTO;
import br.com.ultraworks.erp.api.seguranca.mapper.GrupoAutonomiaMapper;
import br.com.ultraworks.erp.api.seguranca.service.GrupoAutonomiaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/seguranca/grupo-autonomia")
public class GrupoAutonomiaController extends GenericController<GrupoAutonomia, Long, GrupoAutonomiaDTO> {

	public GrupoAutonomiaController(GrupoAutonomiaService service, GrupoAutonomiaMapper mapper) {
		super(service, mapper);
	}
}