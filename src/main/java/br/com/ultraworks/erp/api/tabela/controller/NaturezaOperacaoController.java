package br.com.ultraworks.erp.api.tabela.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.tabela.domain.naturezaOperacao.NaturezaOperacao;
import br.com.ultraworks.erp.api.tabela.domain.naturezaOperacao.NaturezaOperacaoDTO;
import br.com.ultraworks.erp.api.tabela.mapper.NaturezaOperacaoMapper;
import br.com.ultraworks.erp.api.tabela.service.NaturezaOperacaoService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/tabela/natureza-operacao")
public class NaturezaOperacaoController extends GenericController<NaturezaOperacao, Long, NaturezaOperacaoDTO> {

	public NaturezaOperacaoController(NaturezaOperacaoService service, NaturezaOperacaoMapper mapper) {
		super(service, mapper);
	}

}
