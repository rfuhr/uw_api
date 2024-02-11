package br.com.ultraworks.erp.api.tabela.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.tabela.domain.grupooperacaointerna.GrupoOperacaoInterna;
import br.com.ultraworks.erp.api.tabela.domain.grupooperacaointerna.GrupoOperacaoInternaDTO;
import br.com.ultraworks.erp.api.tabela.mapper.GrupoOperacaoInternaMapper;
import br.com.ultraworks.erp.api.tabela.service.GrupoOperacaoInternaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/tabela/grupo-operacao-interna")
public class GrupoOperacaoInternaController extends GenericController<GrupoOperacaoInterna, Long, GrupoOperacaoInternaDTO> {

	public GrupoOperacaoInternaController(GrupoOperacaoInternaService service, GrupoOperacaoInternaMapper mapper) {
		super(service, mapper);
	}

}
