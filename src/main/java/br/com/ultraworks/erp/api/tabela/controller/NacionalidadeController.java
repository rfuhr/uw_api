package br.com.ultraworks.erp.api.tabela.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.tabela.domain.nacionalidade.Nacionalidade;
import br.com.ultraworks.erp.api.tabela.domain.nacionalidade.NacionalidadeDTO;
import br.com.ultraworks.erp.api.tabela.mapper.NacionalidadeMapper;
import br.com.ultraworks.erp.api.tabela.service.NacionalidadeService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/tabela/nacionalidade")
public class NacionalidadeController extends GenericController<Nacionalidade, Long, NacionalidadeDTO> {

	public NacionalidadeController(NacionalidadeService service, NacionalidadeMapper mapper) {
		super(service, mapper);
	}

}