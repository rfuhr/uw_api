package br.com.ultraworks.erp.api.tabela.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.tabela.domain.profissao.Profissao;
import br.com.ultraworks.erp.api.tabela.domain.profissao.ProfissaoDTO;
import br.com.ultraworks.erp.api.tabela.mapper.ProfissaoMapper;
import br.com.ultraworks.erp.api.tabela.service.ProfissaoService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/tabela/profissao")
public class ProfissaoController extends GenericController<Profissao, Long, ProfissaoDTO> {

	public ProfissaoController(ProfissaoService service, ProfissaoMapper mapper) {
		super(service, mapper);
	}

}
