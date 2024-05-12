package br.com.ultraworks.erp.api.estoque.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.estoque.domain.grupocontabil.GrupoContabil;
import br.com.ultraworks.erp.api.estoque.domain.grupocontabil.GrupoContabilDTO;
import br.com.ultraworks.erp.api.estoque.mapper.GrupoContabilMapper;
import br.com.ultraworks.erp.api.estoque.service.GrupoContabilService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/estoque/grupo-contabil")
public class GrupoContabilController extends GenericController<GrupoContabil, Long, GrupoContabilDTO> {

	public GrupoContabilController(GrupoContabilService service, GrupoContabilMapper mapper) {
		super(service, mapper);
	}

}