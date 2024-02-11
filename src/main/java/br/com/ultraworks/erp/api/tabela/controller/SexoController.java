package br.com.ultraworks.erp.api.tabela.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.tabela.domain.sexo.Sexo;
import br.com.ultraworks.erp.api.tabela.domain.sexo.SexoDTO;
import br.com.ultraworks.erp.api.tabela.mapper.SexoMapper;
import br.com.ultraworks.erp.api.tabela.service.SexoService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/tabela/sexo")
public class SexoController extends GenericController<Sexo, Long, SexoDTO> {

	public SexoController(SexoService service, SexoMapper mapper) {
		super(service, mapper);
	}

}