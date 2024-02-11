package br.com.ultraworks.erp.api.tabela.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInternaDTO;
import br.com.ultraworks.erp.api.tabela.mapper.OperacaoInternaMapper;
import br.com.ultraworks.erp.api.tabela.service.OperacaoInternaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/tabela/operacao-interna")
public class OperacaoInternaController extends GenericController<OperacaoInterna, Long, OperacaoInternaDTO> {

	public OperacaoInternaController(OperacaoInternaService service, OperacaoInternaMapper mapper) {
		super(service, mapper);
	}

}