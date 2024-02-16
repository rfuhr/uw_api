package br.com.ultraworks.erp.api.estoque.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.estoque.domain.linha.Linha;
import br.com.ultraworks.erp.api.estoque.domain.linha.LinhaDTO;
import br.com.ultraworks.erp.api.estoque.mapper.LinhaMapper;
import br.com.ultraworks.erp.api.estoque.service.LinhaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/estoque/linha")
public class LinhaController extends GenericController<Linha, Long, LinhaDTO> {

	public LinhaController(LinhaService service, LinhaMapper mapper) {
		super(service, mapper);
	}

}