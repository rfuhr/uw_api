package br.com.ultraworks.erp.api.tabela.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.tabela.domain.cidade.Cidade;
import br.com.ultraworks.erp.api.tabela.domain.cidade.CidadeDTO;
import br.com.ultraworks.erp.api.tabela.mapper.CidadeMapper;
import br.com.ultraworks.erp.api.tabela.service.CidadeService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/tabela/cidade")
public class CidadeController extends GenericController<Cidade, Long, CidadeDTO> {

	public CidadeController(CidadeService service, CidadeMapper mapper) {
		super(service, mapper);
	}

}